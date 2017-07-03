using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Epam.JDI.Core.Interfaces.Base;

namespace JDI_Core.Interfaces.Complex.Tables
{
    public interface ITableLine
    {
        /**
     * Get Columns/Rows count
     */
        int Count();
        int Count(bool acceptEmpty);

        int Size();
        //{ return count(); }

        /**
         * Get Columns/Rows headers
         */
            List<string> Headers();

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
            Dictionary<string, Dictionary<string, ICell>> get();

            Dictionary<string, Dictionary<string, string>> getAsText();

            Dictionary<string, ISelect> header();
            ISelect header(string name);
            void RemoveHeaders(string names);
            void AddHeaders(string names);
            void clean();

            void setCount(int value);

            int getStartIndex();
    }
}
