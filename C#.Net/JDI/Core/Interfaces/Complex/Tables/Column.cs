using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JDI_Core.Interfaces.Complex.Tables
{
    public class Column : RowColumn
    {
        public Column(int num) : base(num)
        {

        }

        public Column(string name) : base(name)
        {

        }

        public static Column column(int num)
        {
            return new Column(num);
        }

        public static Column column(string name)
        {
            return new Column(name);
        }
        public static Column InColumn(int num)
        {
            return new Column(num);
        }

        public static Column InColumn(string name)
        {
            return new Column(name);
        }
    }
}
