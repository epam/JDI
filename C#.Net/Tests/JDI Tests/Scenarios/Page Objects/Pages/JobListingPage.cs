using JDI_Tests.Scenarios.Enums;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Complex.table;
using JDI_Web.Selenium.Elements.Complex.table.interfaces;
using JDI_Web.Selenium.Elements.Composite;
using OpenQA.Selenium;
using static JDI_Web.Selenium.Elements.Complex.table.Column;

namespace JDI_Tests.Scenarios.Page_Objects.Pages
{
    public class JobListingPage : WebPage
    {
        [FindBy(ClassName = "search-result-list")]
        public ITable JobsList = new Table(null,
                By.XPath(".//li[{0}]//div"),
                By.XPath(".//li//div[{0}]"))
                .HasColumnHeaders(typeof(JobListHeaders));

        public void GetJobRowByName(string jobName)
        {
            var row = JobsList.Row(jobName, column(JobListHeaders.JobName));
            row[JobListHeaders.Apply].Select();
        }
    }
}
