using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Composite;
using NUnit.Framework;
using OpenQA.Selenium.Support.PageObjects;
using JDI_Web.Selenium.Elements.Complex.Table;
using JDI_Tests.Scenarios.Entities;

namespace JDI_Tests.Scenarios.Tests
{
    [TestFixture]
    public class FrameTests
    {
        [Test]
        public void FrameTest()
        {
            W3CSite.W3CPage.Open();
            W3CSite.W3CPage.Frame.CSS.Click();
        }
    }

    [Site(Domain = "http://www.w3schools.com/", DemoMode = false)]
    public class W3CSite : WebSite
    {
        [Page(Url = "http://www.w3schools.com/html/html_iframe.asp")]
        public static W3CPageWithFrame W3CPage;
        [Page(Url = "http://www.w3schools.com/html/html_tables.asp")]
        public static TablePage TablePage;
    }

    public class W3CPageWithFrame : WebPage
    {
        [Frame(ByXPath = "//iframe[@src='default.asp']")]
        public ExampleFrame Frame;
    }
    public class TablePage : WebPage
    {
        [FindBy(Id = "customers")]
        public Table<CompanyInfo> Companies = new Table<CompanyInfo> { RowFrom = 2 };
    }

    public class ExampleFrame : Section
    {
        [FindsBy(How = How.LinkText, Using = "CSS")]
        public Link CSS;
    }
}
