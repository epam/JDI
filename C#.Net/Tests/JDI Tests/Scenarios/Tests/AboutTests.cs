using Epam.JDI.Web.Selenium.Elements.Common;
using Epam.Tests.Scenarios.Entities;
using NUnit.Framework;
using static Epam.Tests.Scenarios.Enums.HeaderMenuValues;
using static Epam.Tests.Scenarios.Page_Objects.EpamSite;

namespace Epam.Tests.Scenarios.Tests
{
    [TestFixture]
    public class AboutTests
    {
        [Test]
        public void AboutTest()
        {
            HeaderMenu.Select(ABOUT);
            AboutPage.CheckOpened();
            AboutPage.AboutText.WaitText("We collaborate with you to solve your biggest business challenges");
            AboutPage.AboutCategoriesSection.Company.Highlight();
            Assert.IsTrue(AboutPage.AboutCategoriesSection.Company.GetSource().Contains("/content/dam/epam/company/"), 
                "The source for the page is incorrect!!!");
            AboutPage.AboutCategoriesSection.Company.Click();
            AboutCompanyPage.CheckOpened();
            AboutCompanyPage.Back();
            AboutPage.AboutCategoriesSection.Newsroom.Click();
        }
    }
}
