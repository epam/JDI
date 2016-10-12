using JDI_UIWebTests.UIObjects.Sections;
using JDI_Web.Selenium.Elements.Complex.Table;
using JDI_Web.Selenium.Elements.Complex.Table.Interfaces;
using JDI_Web.Selenium.Elements.Composite;
using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JDI_UIWebTests.UIObjects.Pages
{
    public class SimpleTablePage:WebPage
    {
        private Table simpleTable;
        public JdiPaginator Paginator;
    }
}
