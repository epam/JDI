using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    public class Row : RowColumn
    {
        public Row (int num = -1, string name = null) : base(num, name) { }
        public static Row row(int num)
        {
            return new Row(num);
        }

        public static Row row(string name)
        {
            return new Row(name: name);
        }
    }
}
