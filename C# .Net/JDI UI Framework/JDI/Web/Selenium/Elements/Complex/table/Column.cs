using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    public class Column : RowColumn
    {
        public Column(string name) : base(name)
        {

        }

        private Column(int num) : base(num)
        {

        }

        public static Column column(int num)
        {
            return new Column(num);
        }

        public static Column column(String name)
        {
            return new Column(name);
        }
    }
}
