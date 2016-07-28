using System.Collections.Generic;
using JDI_Web.Selenium.Elements.Base;

namespace JDI_Web.Selenium.Elements.Complex.table.interfaces
{
    public interface ITableLine
    {
        int Count { get;  }

        /**
         * Get Columns/Rows headers
         */
        IList<string> Headers { get; }

        /**
         * Get Columns/Rows in following format <br>
         * For rows: rowName>columnName>cell <br>
         * For columns: columnName>rowName>cell <br>
         * e.g. myTable.columns().get().get("Name").get("5")
         * or   myTable.columns().get().count()
         * But better to use specified commands like
         * cell("Name, "5")
         * myTable.columns().count()
         */
        Dictionary<string, Dictionary<string, ICell>> Get();

        Dictionary<string, Dictionary<string, string>> AsText { get; }

        Dictionary<string, SelectableElement> Header();

        SelectableElement Header(string name);
        void AddHeaders(IList<string> headers);

    }
}