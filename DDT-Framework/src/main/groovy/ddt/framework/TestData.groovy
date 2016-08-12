package ddt.framework

import ddt.framework.model.Body
import ddt.framework.model.Data
import ddt.framework.model.Headers
import ddt.framework.model.Parameter
import ddt.framework.model.search.Product
import org.apache.commons.lang3.text.StrSubstitutor

/**
 * Class to navigate through data file, get data for test step execution, resolve property and variable references in data.
 */
class TestData {

    final static CELL_DESCRIPTION = 0
    // Step description, row will be skipped if first symbol in cell is COMMENT_SYMBOL
    final static CELL_ENDPOINT = 1
    // Endpoint for REST Request. If it is empty then default one from property should be used
    final static CELL_METHOD = 2                // Name of Test Step which should be called
    final static CELL_PATH = 3
    // Resource path for REST Request like "/manage/info", time duration for WAIT test step or description for manual step PAUSE
    final static CELL_ASSERTION_RESULT = 4
    // Map of expected results for HTTP Status, Content-Type and response assertion scripts. Map defined in following format: [�status�: true, �content-type�: false, �response�: true]
    final static CELL_RESPONSE_BODY = 5         // Expected response body (@Skip to avoid data assertion)
    final static CELL_RESPONSE_EVAL_SCRIPT = 6
    // Evaluate script to process actual result before assertion with CELL_RESPONSE_BODY
    final static CELL_RESPONSE_CONTENT_TYPE = 7 // Expected content-type of response (use @Skip to avoid data assertion)
    final static CELL_RESPONSE_STATUS = 8       // Expected HTTP code of response (use @Skip to avoid data assertion)
    final static CELL_REQUEST_MEDIA_TYPE = 9    // Media-type of request
    final static CELL_REQUEST_BODY = 10         // Request body
    final static CELL_REQUEST_HEADERS = 12      // Evaluate script to process request body
    final static CELL_REQUEST_PARAMETERS = 14   // Cells with parameter set

    final static private PREFIX_PROPERTY = '%{' // Prefix for property substitution
    final static private SUFFIX_PROPERTY = '}'  // Suffix for property substitution
    final static private PREFIX_VARIABLE = '${' // Prefix for variable substitution
    final static private SUFFIX_VARIABLE = '}'  // Suffix for variable substitution
    final static private PREFIX_PRODUCT = '@{' // Prefix for product substitution
    final static private SUFFIX_PRODUCT = '}'  // Suffix for product substitution

    final static private EXCEL_FILE_SHEET = 'autoTests'  // Name of Excel spreadsheet to read		
	
    private String COMMENT_SYMBOL = "#"         // Prefix for commented rows in test data file

    private Integer countColumns
    private Integer countRows
    private Integer currentRow
    private ExcelFile xlsFile


    TestData(String filePath) {
        if (filePath)
            xlsFile = new ExcelFile(filePath,EXCEL_FILE_SHEET)
        else
            xlsFile = null
        countRows = xlsFile?.getRows()?.toInteger() ?: 0
        countColumns = xlsFile?.getColumns()?.toInteger() ?: 0
        currentRow = 0
    }	

	// Used by External tools 
    TestData(TestData source, Integer row) {
        xlsFile = source.xlsFile
        countRows = source.countRows
        countColumns = source.countColumns
        currentRow = row
    }
	
    Integer getCountColumns() {
        return countColumns
    }

    Integer getCountRows() {
        return countRows
    }

    Integer getCurrentRow() {
        return currentRow
    }

    void setCurrentRow(Integer Row) {
	    currentRow = Row
	}	
	
    boolean isLastRow() {
        return currentRow >= countRows
    }

    /**
     * Verify if CELL_DESCRIPTION in current row is empty.
     *
     * @return Return true if CELL_DESCRIPTION in current row is empty or if Excel file is not defined.
     */
    boolean isRowDescriptionEmpty() {
        if (xlsFile)
            return xlsFile.isCellEmpty(CELL_DESCRIPTION, currentRow)
        return true
    }

    /**
     * Verify if CELL_DESCRIPTION in current row is started with COMMENT_SYMBOL.
     *
     * @return Return true if CELL_DESCRIPTION in current row is not empty, is in the cells range and starts with COMMENT_SYMBOL.
     */
    boolean isRowCommented() {
        return !isLastRow() &&
                !isRowDescriptionEmpty() &&
                xlsFile?.getCellContents(CELL_DESCRIPTION, currentRow)?.startsWith(COMMENT_SYMBOL)
    }

    /**
     * Skip all commented rows and update currentRow with the next non-commented row number.
     *
     * @return Return row number with the next non-commented row, or the last non-empty row or the last row in file
     */
    Integer getNextRow() {
        while (isRowCommented())
            ++currentRow
        return currentRow
    }

    /**
     * Get values in currentRow from test data file.
     *
     * @return Return Data object with values in currentRow from test data file or null if currentRow is empty or if it is the end of test data file
     */
    Data getData() {
        if (!isLastRow() &&
                !isRowDescriptionEmpty()) {
            return new Data(
                    description: xlsFile?.getCellContents(CELL_DESCRIPTION, currentRow),
                    endpoint: xlsFile?.getCellContents(CELL_ENDPOINT, currentRow)?.trim(),
                    method: xlsFile?.getCellContents(CELL_METHOD, currentRow)?.trim(),
                    resourcePath: xlsFile?.getCellContents(CELL_PATH, currentRow)?.trim(),
                    expectedResponse: getExpectedResponse(),
                    mediaType: xlsFile?.getCellContents(CELL_REQUEST_MEDIA_TYPE, currentRow)?.trim(),
                    status: xlsFile?.getCellContents(CELL_RESPONSE_STATUS, currentRow)?.trim(),
                    contentType: xlsFile?.getCellContents(CELL_RESPONSE_CONTENT_TYPE, currentRow)?.trim(),
                    expectedAssertionResults: xlsFile?.getCellContents(CELL_ASSERTION_RESULT, currentRow),
                    requestBody: getRequestBody(),
                    requestHeaders: getRequestHeaders(),
                    parameters: getParameters()
            )
        }
        return null
    }

    /**
     * Get expected response and evaluation script in currentRow from test data fields.
     *
     * @return Return Body object with expected response and evaluation script
     */
    private Body getExpectedResponse() {
        new Body(
                body: xlsFile?.getCellContents(CELL_RESPONSE_BODY, currentRow)?.trim(),
                evalScript: xlsFile?.getCellContents(CELL_RESPONSE_EVAL_SCRIPT, currentRow)?.trim()
        )
    }

    /**
     * Get request body and evaluation script in currentRow from test data fields.
     *
     * @return Return Body object with request body and evaluation script
     */
    private Body getRequestBody() {
        new Body(
                body: xlsFile?.getCellContents(CELL_REQUEST_BODY, currentRow)?.trim(),
                evalScript: xlsFile?.getCellContents(CELL_REQUEST_BODY + 1, currentRow)?.trim()
        )
    }

    /**
     * Get request headers and evaluation script in currentRow from test data fields.
     *
     * @return Return Headers object with request headers and evaluation script
     */
    private Headers getRequestHeaders() {
        new Headers(
                headers: xlsFile?.getCellContents(CELL_REQUEST_HEADERS, currentRow),
                evalScript: xlsFile?.getCellContents(CELL_REQUEST_HEADERS + 1, currentRow)?.trim()
        )
    }

    /**
     * Get all request parameters in currentRow from test data file.
     *
     * Each Parameter is defined by three columns: parameter name, parameter value and evaluation script. All parameters are stored in array.
     *
     * @return Return Parameter Array
     */
    Parameter[] getParameters() {
        Parameter[] parameterList = new Parameter[((countColumns - CELL_REQUEST_PARAMETERS) / 3 + 1) as Integer]
        def currentColumn = CELL_REQUEST_PARAMETERS
        while (currentColumn < countColumns) {
            Parameter parameter = null
            if (!xlsFile.isCellEmpty(currentColumn, currentRow))
                parameter = new Parameter(
                        name: xlsFile.getCellContents(currentColumn, currentRow)?.trim(),
                        value: !xlsFile.isCellEmpty(currentColumn + 1, currentRow) ?
                                xlsFile.getCellContents(currentColumn + 1, currentRow) : "",
                        evalScript: !xlsFile.isCellEmpty(currentColumn + 2, currentRow) ?
                                xlsFile.getCellContents(currentColumn + 2, currentRow)?.trim() : ""
                )

            parameterList[(currentColumn - CELL_REQUEST_PARAMETERS) / 3 as Integer] = parameter
            currentColumn += 3
        }
        return parameterList
    }

    private static StrSubstitutor getVariableSubstitutor(Map<String, String> variablesMap) {
        StrSubstitutor substitutor = new StrSubstitutor(variablesMap, PREFIX_VARIABLE, SUFFIX_VARIABLE)
        // Enable Substitution in Variables
        substitutor.setEnableSubstitutionInVariables(true)

        return substitutor
    }

    private static StrSubstitutor getPropertySubstitutor(Properties properties) {
        Map<String, String> propertyMap = [:]
        properties?.stringPropertyNames()?.each { name ->
            propertyMap.put(name, properties.getProperty(name))
        }

        StrSubstitutor substitutor = new StrSubstitutor(propertyMap, PREFIX_PROPERTY, SUFFIX_PROPERTY)
        // Enable Substitution in Variables
        substitutor.setEnableSubstitutionInVariables(true)

        return substitutor
    }

    /**
     * Substitute variable references in data fields with values from Map.
     *
     * @param data - Data object with variable references.
     * @param variablesMap - Map of variable values to resolve variable references in Data.
     */
    static void substituteVariablesInRequest(Data data, Map<String, String> variablesMap) {
        StrSubstitutor substitutor = getVariableSubstitutor(variablesMap)
        data?.substituteRequest(substitutor)
    }

    static void substituteVariablesInResponse(Data data, Map<String, String> variablesMap) {
        StrSubstitutor substitutor = getVariableSubstitutor(variablesMap)
        data?.substituteResponse(substitutor)
    }

    /**
     * Substitute property references in data fields with values from Properties.
     *
     * @param data - Data object with property references.
     * @param properties - Properties to resolve property references in Data.
     */
    static void substitutePropertiesInRequest(Data data, Properties properties) {
        StrSubstitutor substitutor = getPropertySubstitutor(properties)

        data?.substituteRequest(substitutor)
    }

    static void substitutePropertiesInResponse(Data data, Properties properties) {
        StrSubstitutor substitutor = getPropertySubstitutor(properties)

        data?.substituteResponse(substitutor)
    }

    static void substitutesProductInData(Data data, Map<String, Product> productsMap) {
        productsMap.each { product ->
            def lookup = new JsonStrLookup<>(product.value, product.key)
            StrSubstitutor substitutor = new StrSubstitutor(lookup, PREFIX_PRODUCT, SUFFIX_PRODUCT, StrSubstitutor.DEFAULT_ESCAPE)

            data?.substituteRequest(substitutor)
            data?.substituteResponse(substitutor)
        }
    }

    static void substitutesProductInRequest(Data data, Map<String, Product> productsMap) {
        productsMap.each { product ->
            def lookup = new JsonStrLookup<>(product.value, product.key)
            StrSubstitutor substitutor = new StrSubstitutor(lookup, PREFIX_PRODUCT, SUFFIX_PRODUCT, StrSubstitutor.DEFAULT_ESCAPE)

            data?.substituteRequest(substitutor)
        }
    }

    static void substitutesProductInResponse(Data data, Map<String, Product> productsMap) {
        productsMap.each { product ->
            def lookup = new JsonStrLookup<>(product.value, product.key)
            StrSubstitutor substitutor = new StrSubstitutor(lookup, PREFIX_PRODUCT, SUFFIX_PRODUCT, StrSubstitutor.DEFAULT_ESCAPE)

            data?.substituteResponse(substitutor)
        }
    }
}