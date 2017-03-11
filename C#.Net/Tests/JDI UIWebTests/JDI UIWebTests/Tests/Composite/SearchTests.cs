using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;

namespace JDI_UIWebTests.Tests.Composite
{
    public class SearchTests
    {

        [SetUp]
        public void SetUp()
        {
            Logger.Info("Navigating to Home page.");
            HomePage.Open();
            HomePage.CheckTitle();
            HomePage.IsOpened();
            Logger.Info("Setup method finished");
            Logger.Info("Start test: " + TestContext.CurrentContext.Test.Name);
        }

        [Test]
        public void FillTest()
        {
            Header.Search.SearchButton.Click();
            Header.Search.Find("something");
            Header.Search.SearchButonActive.Click();
            SupportPage.CheckOpened();
        }

    }
}
