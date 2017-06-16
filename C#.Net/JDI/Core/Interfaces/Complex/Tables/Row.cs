using JDI_Core.Interfaces.Complex.Tables;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JDI_Core.Interfaces.Complex.Tables
{
    public class Row : RowColumn
    {
        public Row(int num) : base(num)
        {

        }

        public Row(string name) : base(name)
        {

        }

        public static Row row(int num)
        {
            return new Row(num);
        }

        public static Row row(string name)
        {
            return new Row(name);
        }

        public static Row InRow(int num)
        {
            return new Row(num);
        }

        public static Row InRow(string name)
        {
            return new Row(name);
        }
    }
}
