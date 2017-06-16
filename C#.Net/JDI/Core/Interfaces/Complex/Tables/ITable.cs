using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Common;
using JDI_Core.Interfaces.Complex.Tables;
using System.Runtime.Remoting.Messaging;

namespace JDI_Core.Interfaces.Complex.Tables
{
    public interface ITable : IText
    {
        /**
     * Get Cell by column/row index (Int) or name(string)
     */
        ICell Cell(Column column, Row row);

        ICell Cell(string columnName, string rowName);
        //{
            //return cell(Column.column(columnName), Row.row(rowName));
        //}

        ICell Cell(int columnIndex, int rowIndex);
       /*{
            if (columnIndex <= 0 || rowIndex <= 0)
                throw new ArgumentException("Table indexes starts from 1");
            return cell(Column.column(columnIndex), Row.row(rowIndex));
        }*/


        ICell Cell(int columnIndex, string rowName);
        /*{
            if (columnIndex <= 0)
                throw new ArgumentException("Table indexes starts from 1");
            return cell(Column.column(columnIndex), Row.row(rowName));
        }*/


        ICell Cell(string columnName, int rowIndex);
        /*{
            if (rowIndex <= 0)
                throw new ArgumentException("Table indexes starts from 1");
            return cell(Column.column(columnName), Row.row(rowIndex));
        }*/
        /**
         * Get all Cells with values equals to searched value
         */
        
            List<ICell> Cells(string value);

        /**
         * Get first Cell in row with value contains expected value
         */
        
            ICell CellContains(string value, Row row);

        /**
         * Get first Cell in row with value match expected regEx
         */
        
            ICell CellMatch(string regex, Row row);

        /**
         * Get first Cell in column with value contains expected value
         */
        
            ICell CellContains(string value, Column column);

        /**
         * Get first Cell in column with value match expected regEx
         */
        
            ICell CellMatch(string regex, Column column);
        /**
         * Get all Cells with values contains expected value
         */
        
            List<ICell> CellsContains(string value);

        /**
         * Get all Cells with values matches to searched regex
         */
        
            List<ICell> CellsMatch(string regex);

        /**
         * Get first Cell with equals to searched value
         */
        
            ICell cell(string value);

        /**
         * Get first Cell with matches to searched regex
         */
        
            ICell cellMatch(string regex);

        /**
         * Searches Rows in table matches specified criteria colNameValues - list of search criteria in format columnName=columnValue<br>
         * = - Equals
         * ~= - Contains
         * *= - Match RegEx
         * e.g. rows("Name=Roman", "Profession=QA") <br>
         * e.g. rows("Name*=.* +*", "Profession~=Test") <br>
         * Each Row is map: columnName:cell
         */
        
            Dictionary<string, Dictionary<string, ICell>> rows(string colNameValues);

        Dictionary<string, Dictionary<string, ICell>> rows(string value, Column column);
        Dictionary<string, Dictionary<string, ICell>> rowsContains(string value, Column column);
        Dictionary<string, Dictionary<string, ICell>> rowsMatches(string regEx, Column column);
        /**
         * Searches Columns in table matches specified criteria rowNameValues - list of search criteria in format rowName=rowValue<br>
         * = - Equals
         * ~= - Contains
         * *= - Match RegEx
         * e.g. columns("Total=100", "Count=10") <br>
         * e.g. columns("Total*=\\d+", "Profession~=QA") <br>
         * Each Column is map: rowName:cell
         */
        
            Dictionary<string, Dictionary<string, ICell>> columns(string rowNameValues);

        Dictionary<string, Dictionary<string, ICell>> columns(string value, Row row);
        Dictionary<string, Dictionary<string, ICell>> columnsContains(string value, Row row);
        Dictionary<string, Dictionary<string, ICell>> columnsMatches(string regEx, Row row);

        /**
         * Waits while value appear in Row <br>
         * e.g. waitValue("100", row("Total")) <br>
         * or   waitValue("100", row(5))
         */
        
            bool waitValue(string value, Row row);

        /**
         * Waits while value appear in Column <br>
         * e.g. waitValue("Roman", column("Name")) <br>
         * or   waitValue("Roman", column(3))
         */
        
            bool waitValue(string value, Column column);

        /**
         * Indicates are any rows in table. Check immediately
         */
        
            bool isEmpty();

        /**
         * Wait while at least one row appear in table
         */
        
            bool waitHaveRows();

        /**
         * Wait while at least count of rows appear in table
         */
        
            bool waitRows(int count);

        /**
         * Get first Cell with searched value in row by index (Int) or name(string)<br>
         * e.g. cell("100", row("Total")) <br>
         * or   cell("100", row(5))
         */
        
            ICell cell(string value, Row row);

        /**
         * Get first Cell with searched value in column by index (Int) or name(string)<br>
         * e.g. cell("Roman", column("Name")) <br>
         * or   cell("Roman", column(3))
         */
        
            ICell cell(string value, Column column);

        /**
         * Get all Cells with values matches to searched in Row by index (Int) or name(string) <br>
         * e.g. cellsMatch(".*uccess.*", row("Result")) <br>
         * or   cellsMatch(".*uccess.*", row(5))
         */
        
            List<ICell> cellsMatch(string regex, Row row);

        /**
         * Get all Cells with values matches to searched in Column by index (Int) or name(string) <br>
         * e.g. cellsMatch("Roma.*", column("Name")) <br>
         * or   cellsMatch("Roma.*", column(3))
         */
        
            List<ICell> cellsMatch(string regex, Column column);

        /**
         * Get Row cells for Cell with searched value in Column by index(Int) or name(string) <br>
         * e.g. row("Roman", column("Name")) <br>
         * or   row("Roman", column(3)) <br>
         * Each Row is map: columnName:cell
         */
        
            Dictionary<string, ICell> row(string value, Column column);

        /**
         * Get Column cells for Cell with searched value in Row by index(Int) or name(string) <br>
         * e.g. column("100", row("Total") <br>
         * or   column("100", row(5)) <br>
         * Each Column is map: rowName:cell
         */
        
            Dictionary<string, ICell> column(string value, Row row);
        /**
         * Get Column cells for Cell with searched value contains in Row's values by index(Int) or name(string) <br>
         * e.g. columnContains("Framewo", row("Total") <br>
         * or   columnContains("mwork", row(5)) <br>
         * Each Column is map: rowName:cell
         */
        
            Dictionary<string, ICell> columnContains(string value, Row row);
        /**
         * Get Column cells for Cell with Row's values match regEx by index(Int) or name(string) <br>
         * e.g. columnContains(".*work", row("Total") <br>
         * or   columnContains("Frame.+ork", row(5)) <br>
         * Each Column is map: rowName:cell
         */
        
            Dictionary<string, ICell> columnMatch(string regEx, Row row);
        /**
         * Get Column cells for Cell with searched value contains in Column's values by index(Int) or name(string) <br>
         * e.g. row("Framewo", column("Name")) <br>
         * or   row("mwork", column(3)) <br>
         * Each Row is map: columnName:cell
         */
        
            Dictionary<string, ICell> rowContains(string value, Column column);
        /**
         * Get Column cells for Cell with Column's values match regEx by index(Int) or name(string) <br>
         * e.g. row(".*work", column("Name")) <br>
         * or   row("Frame.+ork", column(3)) <br>
         * Each Row is map: columnName:cell
         */
        
            Dictionary<string, ICell> rowMatch(string regEx, Column column);

        Row rows();

        /**
         * Get Row with index <br>
         * Each Row is map: columnName:cell
         */
        
            Dictionary<string, ICell> row(int rowNum);

        /**
         * Get Row with name <br>
         * Each Row is map: columnName:cell
         */
        
            Dictionary<string, ICell> row(string rowName);

        /**
         * Get Row value
         */
        
            List<string> rowValue(int colNum);

        /**
         * Get Row value
         */
        
            List<string> rowValue(string colName);

        IColumn columns();

        /**
         * Get Column with index <br>
         * Each Column is map: rowName:cell
         */
        
            Dictionary<string, ICell> column(int colNum);

        /**
         * Get Column with name <br>
         * Each Column is map: rowName:cell
         */
        
            Dictionary<string, ICell> column(string colName);

        /**
         * Get Column value
         */
        
            List<string> columnValue(int colNum);

        /**
         * Get Column value
         */
        
            List<string> columnValue(string colName);

        /**
         * Get Header
         */
        
            Dictionary<string, ISelect> header();

        
            ISelect header(string name);

        /**
         * Get Header
         */
        
            List<string> headers();

        /**
         * Get Footer
         */
        
            List<string> footer();

        /**
         * Get All Cells
         */
        
            List<ICell> getCells();

        /**
         * Clean all already founded Cells
         */
        void clean();

        /**
         * Similar to clean
         */
        void clear();

        ITable useCache(bool value);

        ITable useCache();
        /*{
            return useCache(true);
        }*/

        ITable clone();

        ITable copy();

        ITable hasAllHeaders();

        ITable hasNoHeaders();

        ITable hasOnlyColumnHeaders();

        ITable hasOnlyRowHeaders();

        ITable hasColumnHeaders(List<string> value);

        ITable HasColumnHeaders(Func<Header> headers);

        ITable hasRowHeaders(List<string> value);

        ITable HasRowHeaders(Func<Header> headers);

        ITable setColumnsCount(int value);

        ITable setRowsCount(int value);
    }
}
