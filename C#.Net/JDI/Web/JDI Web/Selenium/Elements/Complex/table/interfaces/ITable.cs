using System.Collections.Generic;
using JDI_Core.Interfaces.Common;
using JDI_Web.Selenium.Elements.Base;

namespace JDI_Web.Selenium.Elements.Complex.table.interfaces
{
    public interface ITable : IText
    {
        /**
        * Get Cell by column/row index (Int) or name(string)
        */
        ICell Cell(Column column, Row row);

        ICell Cell(string columnName, string rowName);

        ICell Cell(int columnIndex, int rowIndex);

        /**
         * Get all Cells with values equals to searched value
         */
        IList<ICell> Cells(string value);

        /**
         * Get all Cells with values matches to searched regex
         */
        IList<ICell> CellsMatch(string regex);

        /**
         * Get first Cell with equals to searched value
         */
        ICell Cell(string value);

        /**
         * Get first Cell with matches to searched regex
         */
        ICell CellMatch(string regex);

        /**
         * Searches Rows in table matches specified criteria colNameValues - list of search criteria in format columnName=columnValue<br>
         * e.g. rows("Name=Roman", "Profession=QA") <br>
         * Each Row is map: columnName:cell
         */
        Dictionary<string, Dictionary<string, ICell>> GetRows(params string[] colNameValues);

        /**
         * Searches Columns in table matches specified criteria rowNameValues - list of search criteria in format rowName=rowValue<br>
         * e.g. columns("Total=100", "Count=10") <br>
         * Each Column is map: rowName:cell
         */
        Dictionary<string, Dictionary<string, ICell>> GetColumns(params string[] rowNameValues);

        /**
         * Waits while value appear in Row <br>
         * e.g. waitValue("100", row("Total")) <br>
         * or   waitValue("100", row(5))
         */
        bool WaitValue(string value, Row row);

        /**
         * Waits while value appear in Column <br>
         * e.g. waitValue("Roman", column("Name")) <br>
         * or   waitValue("Roman", column(3))
         */
        bool WaitValue(string value, Column column);

        /**
         * Indicates are any rows in table. Check immediately
         */
        bool Empty { get; }

        /**
         * Wait while at least one row appear in table
         */
        bool WaitHaveRows();

        /**
         * Wait while at least count of rows appear in table
         */
        bool WaitRows(int count);

        /**
         * Get first Cell with searched value in row by index (Int) or name(string)<br>
         * e.g. cell("100", row("Total")) <br>
         * or   cell("100", row(5))
         */
        ICell Cell(string value, Row row);

        /**
         * Get first Cell with searched value in column by index (Int) or name(string)<br>
         * e.g. cell("Roman", column("Name")) <br>
         * or   cell("Roman", column(3))
         */
        ICell Cell(string value, Column column);

        /**
         * Get all Cells with values matches to searched in Row by index (Int) or name(string) <br>
         * e.g. cellsMatch(".*uccess.*", row("Result")) <br>
         * or   cellsMatch(".*uccess.*", row(5))
         */
        IList<ICell> CellsMatch(string regex, Row row);

        /**
         * Get all Cells with values matches to searched in Column by index (Int) or name(string) <br>
         * e.g. cellsMatch("Roma.*", column("Name")) <br>
         * or   cellsMatch("Roma.*", column(3))
         */
        IList<ICell> CellsMatch(string regex, Column column);

        /**
         * Get Row cells for Cell with searched value in Column by index(Int) or name(string) <br>
         * e.g. row("Roman", column("Name")) <br>
         * or   row("Roman", column(3)) <br>
         * Each Row is map: columnName:cell
         */
        Dictionary<string, ICell> Row(string value, Column column);

        /**
         * Get Column cells for Cell with searched value in Row by index(Int) or name(string) <br>
         * e.g. column("100", row("Total") <br>
         * or   column("100", row(5)) <br>
         * Each Column is map: rowName:cell
         */
        Dictionary<string, ICell> Column(string value, Row row);

        Rows Rows();

        /**
         * Get Row with index <br>
         * Each Row is map: columnName:cell
         */
        Dictionary<string, ICell> Row(int rowNum);

        /**
         * Get Row with name <br>
         * Each Row is map: columnName:cell
         */
        Dictionary<string, ICell> Row(string rowName);

        /**
         * Get Row value
         */
        IList<string> RowValue(int colNum);

        /**
         * Get Row value
         */
        IList<string> RowValue(string colName);

        Columns Columns();

        /**
         * Get Column with index <br>
         * Each Column is map: rowName:cell
         */
        Dictionary<string, ICell> Column(int colNum);

        /**
         * Get Column with name <br>
         * Each Column is map: rowName:cell
         */
        Dictionary<string, ICell> Column(string colName);

        /**
         * Get Column value
         */
        IList<string> ColumnValue(int colNum);

        /**
         * Get Column value
         */
        IList<string> ColumnValue(string colName);

        /**
         * Get Header
         */
        Dictionary<string, SelectableElement> Header();

        SelectableElement Header(string name);

        /**
         * Get Header
         */
        IList<string> Headers();

        /**
         * Get Footer
         */
        IList<string> Footer();

        /**
         * Get All Cells
         */
        IList<ICell> GetCells();

        /**
         * Clean all already founded Cells
         */
        void Clean();

        /**
         * Similar to clean
         */
        void Clear();

        ITable UseCache();

        Table Clone();

        Table Copy();

    }
}