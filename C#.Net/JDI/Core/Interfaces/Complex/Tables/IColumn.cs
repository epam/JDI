using Epam.JDI.Core.Interfaces.Base;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JDI_Core.Interfaces.Complex.Tables
{
    public interface IColumn
    {
        int Count();
        int Count(bool acceptEmpty);
        //int Size() { return Count(); }

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
            Dictionary<string, Dictionary<string, ICell>> Get();

            Dictionary<string, Dictionary<string, string>> GetAsText();

            Dictionary<string, ISelect> Header();

            ISelect Header(string name);
            void RemoveHeaders(string names);
            void AddHeaders(string names);
            void Clean();

            void SetCount(int value);

            int GetStartIndex();
    }
}
