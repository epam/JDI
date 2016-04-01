using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    public class TableSettings
    {
        public bool ColumnHasHeaders { set; get; } = true;
        public bool RowHasHeaders { set; get; } = true;
        public List<String> ColumnHeaders { set; get; }
        public List<String> RowHeaders { set; get; }
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
