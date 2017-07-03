using JDI_Tests.Entities;
using JDI_Tests.Enums;
using JDI_Tests.Epam_UIObjects.Elements;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Complex.Table;
using JDI_Web.Selenium.Elements.Composite;
using OpenQA.Selenium;

namespace JDI_Tests.Epam_UIObjects.Pages
{
    public class JobListingPage : WebPage
    {
        [FindBy(ClassName = "search-result-list")]
        public EntityTable<Job, JobColumn> JobsList = new EntityTable<Job, JobColumn>
        {
            RowBy = By.XPath(".//li[{0}]//div"),
            ColumnBy = By.XPath(".//li//div[{0}]"),
            ColumnHeaders = new [] {"Title", "Type", "Location", "Apply"}
        };
        
        
        public void GetJobRowByName(string jobName)
        {
            var row = JobsList.Row(jobName, Column.column(JobListHeaders.Title));
            row.Apply.Click();
            /*var entity = JobsList2.Entity(jobName, column(JobListHeaders.Title));
            var row2 = JobsList2.Row(jobName, column(JobListHeaders.Title));*/
        }
    }
}
