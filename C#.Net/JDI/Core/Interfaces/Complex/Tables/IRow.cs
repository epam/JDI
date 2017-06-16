using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JDI_Core.Interfaces.Complex.Tables
{
    public interface IRow : ITableLine
    {
        Dictionary<string, ICell> GetRow(string colName);

        List<string> GetRowValue(string colName);

        Dictionary<string, string> GetRowAsText(string colName);

        Dictionary<string, Dictionary<string, ICell>> WithValue(string value, Column row);

        Dictionary<string, Dictionary<string, ICell>> ContainsValue(string value, Column row);

        Dictionary<string, Dictionary<string, ICell>> MatchesRegEx(string regEx, Column row);

        Dictionary<string, ICell> GetRow(int colNum);

        List<string> GetRowValue(int colNum);

        Dictionary<string, string> GetRowAsText(int colNum);

        Dictionary<string, Dictionary<string, ICell>> Get();
    }
}
