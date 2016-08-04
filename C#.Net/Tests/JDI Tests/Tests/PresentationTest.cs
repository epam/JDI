using Epam.JDI.Commons;
using Epam.JDI.Web.Selenium.Elements.Common;
using Epam.Tests.Scenarios.Entities;
using NUnit.Framework;
using NUnit.Framework.Internal;
using static Epam.Tests.Scenarios.Enums.HeaderMenuValues;
using static Epam.Tests.Scenarios.Page_Objects.EpamSite;

namespace Epam.Tests.Scenarios.Tests
{
    [TestFixture]
    public class PresentationTests
    {
        [Test]
        public void NoCareerTest()
        {
            HeaderMenu.Select(CAREERS);
            CareerPage.CheckOpened();
            CareerPage.JobFilter.Keywords.NewInput("C#");
            CareerPage.AutocompleteSuggestList.ClickCenter();
            CareerPage.JobFilter.Category.Select("HR & Talent Acquisition");
            CareerPage.JobSearchButton.Click();
            JobListingPage.CheckOpened();
            JobListingPage.JobsList.WaitText("Sorry, your search returned no results. Please try another query.");
        }

        [Test]
        public void ContactFormTest()
        {
            HeaderMenu.Select(CONTACT);
            ContactPage.ContactUsButton.Click();
            RequestInformationPage.CheckOpened();
            RequestInformationPage.MessageArea.SendKeys("Hello, world!");
            RequestInformationPage.MessageArea.AddNewLine("Everything is awesome!");
        }
    }
}
