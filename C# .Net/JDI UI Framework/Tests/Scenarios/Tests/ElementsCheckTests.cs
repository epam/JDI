using Epam.Tests.Scenarios.Enums;
using NUnit.Framework;
using static Epam.Tests.Scenarios.Page_Objects.EpamSite;

namespace Epam.Tests.Scenarios.Tests
{
    [TestFixture]
    public class ElementsCheckTests
    {
        [Test]
        public void ElementsTest_Textbox_Text()
        {
            HeaderMenu.Select(HeaderMenuValues.CAREERS);
            // CareerPage.CheckOpened();
            Assert.IsTrue(CareerPage.CopyRight.Text.Contains("2016 EPAM"),
                "Invalid Data in copyright string on the footer");
        }
        [Test]
        public void ElementsTest_TextFiled_NewInput()
        {
            HeaderMenu.Select(HeaderMenuValues.CAREERS);
            CareerPage.JobSearchInput.WaitDisplayed();
            CareerPage.JobSearchInput.Highlight();
            CareerPage.JobSearchInput.NewInput("qa");
            CareerPage.AutocompleteSuggestList.WaitDisplayed();
            Assert.IsTrue(CareerPage.AutocompleteSuggestList.Displayed, "suggestlist not displayed");
        }
        [Test]
        public void ElementsTest_Button_Click()
        {
            HeaderMenu.Select(HeaderMenuValues.CAREERS);
            CareerPage.JobSearchButton.Highlight();
            CareerPage.JobSearchButton.WaitDisplayed();
            CareerPage.JobSearchButton.Click();
            CareerPage.SearchResultHeader.WaitDisplayed();
            Assert.IsTrue(CareerPage.SearchResultHeader.Displayed, "Search results not displayed - click action on button not performed");
        }
        [Test]
        public void ElementsTest_Link_Displayed()
        {
            HeaderMenu.Select(HeaderMenuValues.CAREERS);
            CareerPage.JobSearchButton.WaitDisplayed();
            CareerPage.JobSearchButton.Click();
            CareerPage.BestMatchIcon.WaitDisplayed();
            Assert.IsTrue(CareerPage.BestMatchIcon.Displayed, "icon 'Best Match' not displayed");

        }
        [Test]
        public void ElementsTest_Link_GetTooltip()
        {
            HeaderMenu.Select(HeaderMenuValues.CAREERS);
            CareerPage.JobSearchButton.Click();
            CareerPage.SearchResultHeader.WaitDisplayed();
            CareerPage.BestMatchIcon.WaitDisplayed();
            Assert.IsTrue(CareerPage.BestMatchIcon.GetTooltip().Equals("Best Match"), "no tooltip 'Best Match'");
        }

        [Test]
        public void ElementsTest_Link_Text()
        {
            HeaderMenu.Select(HeaderMenuValues.CAREERS);
            Assert.IsTrue(CareerPage.HeaderMenuCrumbs.Text.Contains("Home"), "Breadcrumb of first level is incorrect");
        }

        [Test]
        public void ElementsTest_Link_GetReference()
        {
            HeaderMenu.Select(HeaderMenuValues.CAREERS);
            CareerPage.JobSearchButton.Click();
            Assert.IsTrue(CareerPage.HeaderMenuCrumbs2.WaitReferenceContains("/careers").Contains("/careers"), "failed test Link.WaitReferenceContains()");
            Assert.IsTrue(CareerPage.HeaderMenuCrumbs2.WaitMatchReference("https://www.epam.com/careers").Equals("https://www.epam.com/careers"), "failed test Link.WaitReferenceContains()");
            Assert.IsTrue(CareerPage.HeaderMenuCrumbs2.Displayed, "breadcrumb 2 level");
            Assert.IsTrue(CareerPage.HeaderMenuCrumbs2.GetReference().Equals("https://www.epam.com/careers"),
                "incorrect href value for bredcrumb link of second level");

        }
        [Test]
        public void ElementsTest_Link_Click()
        {
            HeaderMenu.Select(HeaderMenuValues.CAREERS);
            CareerPage.HeaderMenuCrumbs.Click();
            Assert.IsTrue(HomePage.Title.Equals("EPAM | Software Product Development Services"),
               "Title after click on breadcrumb is incorrect, expected for HomePage");
        }
    }
}
