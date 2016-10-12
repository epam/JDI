using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using JDI_Web.Selenium.Elements.Composite;
using static JDI_Web.Settings.WebSettings;

namespace JDI_UIWebTests.Tests.Composite
{
    public class PaginationTests
    {
        Pagination simplePagePaginator = SimpleTablePage.Paginator;

        [SetUp]
        public void SetUp()
        {
            Logger.Info("Navigating to Simple Table page.");
            SimpleTablePage.Open();
            SimpleTablePage.CheckTitle();
            SimpleTablePage.IsOpened();
            Logger.Info("Setup method finished");
            Logger.Info("Start test: " + TestContext.CurrentContext.Test.Name);
        }

        private void CheckPageOpened(int num)
        {
            Assert.True(WebDriver.Url.Contains("/page" + num + ".htm"));            
        }

        [Test]
        public void NextTest()
        {
            simplePagePaginator.Next();
            CheckPageOpened(7);
        }

        
        [Test]
        public void PrevTest()
        {
            simplePagePaginator.Previous();
            CheckPageOpened(5);
        }

        
        [Test]
        public void FirstTest()
        {
            simplePagePaginator.First();
            CheckPageOpened(1);
        }

        
        [Test]
        public void LastTest()
        {
            simplePagePaginator.Last();
            CheckPageOpened(2);
        }
        
    }
}
