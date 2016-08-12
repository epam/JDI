package ddt.framework

import spock.lang.Specification

/**
 * Created by Aleksei_Galkin on 7/15/2015.
 */
class ExcelFileSpec extends Specification {
    def "GetColumns"() {
        def excelFile = new ExcelFile(filePath(file),"")
        expect:
        excelFile.getColumns() == columns

        where:
        file               | columns
        "map.xls"          | 3
        "oneVariable.xls"  | 2
        "empty.xls"        | 0
        "map.xlsx"         | 3
        "oneVariable.xlsx" | 2
        "empty.xlsx"       | 0
    }

    def "Get Last Column"() {
        def excelFile = new ExcelFile(filePath(file),"")
        expect:
        excelFile.getColumns(row) == columns

        where:
        file               | columns | row
        "map.xlsx"         | 2       | 1
        "map.xlsx"         | 3       | 2
        "map.xlsx"         | 0       | 3
        "oneVariable.xlsx" | 2       | 0
        "oneVariable.xlsx" | 0       | 1
        "empty.xlsx"       | 0       | 0
        "empty.xlsx"       | 0       | 2
        "map.xls"          | 2       | 1
        "map.xls"          | 3       | 2
        "map.xls"          | 0       | 3
        "oneVariable.xls"  | 2       | 0
        "oneVariable.xls"  | 0       | 1
        "empty.xls"        | 0       | 0
        "empty.xls"        | 0       | 2
    }

    def "Get Last Row"() {
        def excelFile = new ExcelFile(filePath(file),"")
        expect:
        excelFile.getLastRow() == row

        where:
        file               | row
        "map.xls"          | 4
        "oneVariable.xls"  | 0
        "empty.xls"        | 0
        "map.xlsx"         | 4
        "oneVariable.xlsx" | 0
        "empty.xlsx"       | 0
    }

    def "GetRows"() {
        def xls = new ExcelFile(filePath(file),"")
        expect:
        xls.getRows() == rows

        where:
        file               | rows
        "map.xls"          | 5
        "oneVariable.xls"  | 1
        "empty.xls"        | 0
        "map.xlsx"         | 5
        "oneVariable.xlsx" | 1
        "empty.xlsx"       | 0
    }

    def "GetCellContents"() {
        def excelFile = new ExcelFile(filePath(file),"")
        expect:
        excelFile.getCellContents(column, row) == content

        where:
        file               | column | row | content
        "map.xls"          | 0      | 1   | "second"
        "map.xls"          | 2      | 2   | "# Description"
        "map.xls"          | 0      | 3   | null
        "oneVariable.xls"  | 1      | 0   | "first value"
        "map.xlsx"         | 0      | 1   | "second"
        "map.xlsx"         | 2      | 2   | "# Description"
        "map.xlsx"         | 0      | 3   | null
        "oneVariable.xlsx" | 1      | 0   | "first value"
    }

    def "IsCellEmpty"() {
        def xls = new ExcelFile(filePath(file),"")
        expect:
        xls.isCellEmpty(column, row) == content

        where:
        file               | column | row | content
        "map.xls"          | 0      | 1   | false
        "map.xls"          | 2      | 2   | false
        "map.xls"          | 0      | 3   | true
        "oneVariable.xls"  | 1      | 0   | false
        "map.xlsx"         | 0      | 1   | false
        "map.xlsx"         | 2      | 2   | false
        "map.xlsx"         | 0      | 3   | true
        "oneVariable.xlsx" | 1      | 0   | false
    }

    String filePath(String name) {
        def uri = getClass().classLoader.getResource("ddt/framework/maps/" + name).toURI()
        new File(uri).absolutePath
    }
}
