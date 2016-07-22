using System.Collections.Generic;
using Epam.JDI.Core.Interfaces.Common;
using JDI_Tests.Scenarios.Entities;
using JDI_Web.Selenium.Elements.Complex.table;
using OpenQA.Selenium;

namespace JDI_Tests.Scenarios.Page_Objects.Elements
{
    public class JobTable : Table<Job, JobColumn>
    {
        public JobTable()
        {
            RootBy = By.ClassName("search-result-list");
            RowBy = By.XPath(".//li[{0}]//div");
            ColumnBy = By.XPath(".//li//div[{0}]");
            ColumnHeaders = new[] { "Title", "Type", "Location", "Apply" };
        }
        /*
        public List<ILink> Titles;
        public List<IText> Types;
        public List<IText> Locations;
        public List<ILink> Applies;*/
        
    }
}
