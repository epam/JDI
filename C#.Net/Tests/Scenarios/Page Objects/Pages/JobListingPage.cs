using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Complex.table;
using Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces;
using Epam.JDI.Web.Selenium.Elements.Composite;
using Epam.Tests.Scenarios.Enums;
using OpenQA.Selenium;
using static Epam.JDI.Web.Selenium.Elements.Complex.table.Column;
using static Epam.Tests.Scenarios.Enums.JobListHeaders;

namespace Epam.Tests.Scenarios.Page_Objects.Pages
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
            var row = JobsList.Row(jobName, column(JobName));
            row[Apply].Select();
        }
    }
}
