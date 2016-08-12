package ddt.framework

import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell

/**
 * This class use ss model of org.apache.poi library to parse Excel documents.
 */
class ExcelFile {

    private Sheet sheet

    ExcelFile(String filePath, String sheetName) {
        InputStream is = new FileInputStream(filePath)
        try {
            Workbook wbk = new WorkbookFactory().create(is)
			
			if( sheetName != null && sheetName!= "" )
              sheet = wbk.getSheet(sheetName)
			  
			if ( sheet == null || sheetName == "" || sheetName == null) {
			  sheet = wbk.getSheetAt(0)
			}
        } finally {
            is?.close()
        }
    }

    /**
     * Iterate on not empty rows and get the most right cell number on sheet.
     * @return Return the most right cell number on sheet.
     */
    Integer getColumns() {
        def columns = 0

        def iterator = sheet.rowIterator()
        while (iterator.hasNext()) {
            def row = iterator.next()
            columns = Math.max(columns, row.getLastCellNum())
        }

        return columns
    }

    /**
     * Get the last column number for specific row.
     * @param rowIndex - Index of row
     * @return Return the last column number for specific row.
     */
    Integer getColumns(Integer rowIndex) {
        def row = sheet.getRow(rowIndex)
        row ? row.getLastCellNum() : 0
    }

    /**
     * Get the last row number.
     * @return Return the last row number.
     */
    Integer getLastRow() {
        return sheet.getLastRowNum()
    }

    /**
     * Get rows number.
     * @return Return total number of rows.
     */
    Integer getRows() {
        def lastRow = sheet.getRow(getLastRow())

        if (lastRow)
            return getLastRow() + 1

        return getLastRow()
    }

    /**
     * Get content from specific cell
     *
     * @param column - Column index of cell
     * @param row - Row index of cell
     * @return Return content of specific cell or null if cell is blank.
     */
    String getCellContents(Integer columnIndex, Integer rowIndex) {
        def row = sheet.getRow(rowIndex)

        if (!row)
            return null

        def cell = row.getCell(columnIndex,Row.CREATE_NULL_AS_BLANK)

        if (!cell)
            return null

        // TODO: Refactor this switch block
        switch (cell.cellType) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue()
            case Cell.CELL_TYPE_NUMERIC:
                return new DataFormatter().formatCellValue(cell)
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue().toString()
            default:
                return new DataFormatter().formatCellValue(cell)
        }
    }

    /**
     * Verify if specific cell is blank.
     *
     * @param column - Column index of cell
     * @param row - Row index of cell
     * @return Return true if specified cell is blank or out of range.
     */
    Boolean isCellEmpty(Integer columnIndex, Integer rowIndex) {
        def row = sheet.getRow(rowIndex)

        if (!row)
            return true

        if (!row.getCell(columnIndex))
            return true

        return row.getCell(columnIndex)?.getCellType() == Cell.CELL_TYPE_BLANK
    }
}
