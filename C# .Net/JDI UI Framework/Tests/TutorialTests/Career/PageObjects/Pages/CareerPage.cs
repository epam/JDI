using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Composite;

namespace Epam.Tests.TutorialTests.Career.PageObjects
{
    public class CareerPage : WebPage
    {
        [FindBy(ClassName = "job-search")]
        public JobFilter jobFilter;

    }
}