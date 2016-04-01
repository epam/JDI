using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces;
using static System.String;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    public class RowColumn
    {
        public int Num { get; set; }
        public string Name { get; set; }

        public RowColumn(int num)
        {
            Num = num;
        }
        public RowColumn(string name)
        {
            Name = name;
        }

        public bool HaveName()
        {
            return IsNullOrEmpty(Name);
        }

        public T Get<T>(Func<RowColumn, T> action)
        {
            return action.Invoke(this);
        }
        public T Get<T>(Func<string, T> nameAction, Func<int, T> numAction)
        {
            return HaveName() ? nameAction.Invoke(Name) : numAction.Invoke(Num);
        }
    }
}
