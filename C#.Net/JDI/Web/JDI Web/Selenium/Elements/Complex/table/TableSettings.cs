using System.Collections.Generic;

namespace JDI_Web.Selenium.Elements.Complex.table
{
    public class TableSettings
    {
        public bool ColumnHasHeaders { set; get; } = true;
        public bool RowHasHeaders { set; get; } = true;
        public List<string> ColumnHeaders { set; get; }
        public List<string> RowHeaders { set; get; }
        public int ColumnsCount { set; get; }
        public int RowsCount { set; get; }

        public TableSettings()
        {
            
        }
        public TableSettings(bool columnHasHeaders, bool rowHasHeaders)
        {
            ColumnHasHeaders = columnHasHeaders;
            RowHasHeaders = rowHasHeaders;
        }

        public TableSettings(List<string> columnHeaders, List<string> rowHeaders)
        {
            ColumnHeaders = columnHeaders;
            RowHeaders = rowHeaders;
        }

        public TableSettings(int columnsCount, int rowsCount)
        {
            ColumnsCount = columnsCount;
            RowsCount = rowsCount;
        }
    }
}
