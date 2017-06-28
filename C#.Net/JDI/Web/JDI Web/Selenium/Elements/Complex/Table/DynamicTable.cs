using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using JDI_Core.Interfaces.Complex.Tables;
using OpenQA.Selenium.Support.PageObjects;

namespace JDI_Web.Selenium.Elements.Complex.Table
{
    public class DynamicTable
    {
        public DynamicTable()
        {
            new Table().Cache = false;
        }
    }
}
