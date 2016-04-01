using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    public class Row : RowColumn
    {
        public Row (int num) : base(num) { }
        public Row(string name) : base(name) { }
        //TODO
        public static Row row(int num)
        {
            return new Row(num);
        }

        public static Row row(String name)
        {
            return new Row(name);
        }
    }
}
