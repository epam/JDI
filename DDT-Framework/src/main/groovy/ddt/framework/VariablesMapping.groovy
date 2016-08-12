package ddt.framework

import groovy.util.logging.Log4j

@Log4j
/**
 * Class to read Excel file with variable values for test data and store them as Map.
 */
class VariablesMapping {
    final static CELL_NAME = 0          // Variable name. Variable will be skipped if first symbol in cell is "#"
    final static CELL_VALUE = 1         // Value of variable
    final static CELL_DESCRIPTION = 2   // Description for variable

    final private static String COMMENT_SYMBOL = "#" // Prefix for commented rows in map file

    private Integer countRows
    private ExcelFile xlsFile
    private Map<String, String> variablesMap
	private Map<String, String> productsMap


    VariablesMapping(String filePath) {
        if (filePath)
            xlsFile = new ExcelFile(filePath,"")
        else
            xlsFile = null
        countRows = xlsFile?.getRows()?.toInteger() ?: 0
        variablesMap = readVariablesMap()
		productsMap = [:]
    }

    private boolean isLastRow(Integer row) {
        return row >= countRows
    }

    /**
     * Verify if CELL_NAME in specific row is empty.
     *
     * @param row - Row index
     * @return Return true if CELL_NAME in specific row is empty or if Excel file is not defined.
     */
    private boolean isRowEmpty(Integer row) {
        if (xlsFile)
            return xlsFile.isCellEmpty(CELL_NAME, row)
        return true
    }

    /**
     * Verify if CELL_NAME in specific row is started with COMMENT_SYMBOL.
     * @param row - Row index
     * @return Return true if CELL_NAME in specific row is not empty, is in the cells range and starts with COMMENT_SYMBOL.
     */
    private boolean isRowCommented(Integer row) {
        return !isLastRow(row) &&
                !isRowEmpty(row) &&
                xlsFile?.getCellContents(CELL_NAME, row)?.startsWith(COMMENT_SYMBOL)
    }

    /**
     * Read the Excel file row by row and add variable name and values to Map.
     *
     * All rows should be verified one by one until the last row or an empty row is met. All commented rows should be skipped.
     *
     * @return Map with variable names as map keys and variables values as map values.
     */
    private Map<String, String> readVariablesMap() {
        def map = [:]

        def row = 0
        while (!isLastRow(row) && !isRowEmpty(row)) {

            if (!isRowCommented(row)) {
                def name = xlsFile?.getCellContents(CELL_NAME, row)
                def value = xlsFile?.getCellContents(CELL_VALUE, row)

                map.put(name, value)
            }
            ++row
        }

        return map
    }

    Map<String, String> getVariablesMap() {
        return variablesMap
    }
}
