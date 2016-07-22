using JDI_Tests.Scenarios.Entities;
using JDI_Tests.Scenarios.Enums;
using JDI_Tests.Scenarios.Page_Objects.Elements;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Complex.table;
using JDI_Web.Selenium.Elements.Composite;
using OpenQA.Selenium;
using static JDI_Web.Selenium.Elements.Complex.table.Column;

namespace JDI_Tests.Scenarios.Page_Objects.Pages
{
    public class JobListingPage : WebPage
    {
        [FindBy(ClassName = "search-result-list")]
        public Table<Job, JobColumn> JobsList = new Table<Job, JobColumn>
        {
            RowBy = By.XPath(".//li[{0}]//div"),
            ColumnBy = By.XPath(".//li//div[{0}]"),
            ColumnHeaders = new [] {"Title", "Type", "Location", "Apply"}
        };

        public JobTable JobsList2;


        public void GetJobRowByName(string jobName)
        {
            var row = JobsList.Row(jobName, column(JobListHeaders.Title));
            row.Apply.Click();
            /*var entity = JobsList2.Entity(jobName, column(JobListHeaders.Title));
            var row2 = JobsList2.Row(jobName, column(JobListHeaders.Title));*/
        }
    }
}
