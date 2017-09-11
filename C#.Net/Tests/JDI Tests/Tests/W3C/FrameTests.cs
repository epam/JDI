using JDI_Tests.Entities;
using JDI_Web.Attributes;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Complex.Table;
using JDI_Web.Selenium.Elements.Composite;
using NUnit.Framework;
using OpenQA.Selenium.Support.PageObjects;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_Tests.Tests.W3C.W3CSite;
using static JDI_Web.Settings.WebSettings;

namespace JDI_Tests.Tests.W3C
{
    [TestFixture]
    public class FrameTests
    {
        [OneTimeSetUp]
        public void Init()
        {
            InitNUnitDefault();
            Logger.Info("Init test run");
            WinProcUtils.KillAllRunWebDrivers();

            if (!DriverFactory.HasDrivers())
                UseDriver(DriverTypes.Chrome);
            
            WebSite.Init(typeof(W3CSite));
            Logger.Info("Run Tests");
        }

        [Test]
        public void FrameTest()
        {
            W3CPage.IsOpened();
            W3CPage.Frame.CSS.Click();
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
        [Frame(XPath = "//iframe[@src='default.asp']")]
        public ExampleFrame Frame;
    }
    public class TablePage : WebPage
    {
        [FindBy(Id = "customers")]
        public EntityTable<CompanyInfo> Companies = new EntityTable<CompanyInfo> { RowFrom = 2 };
        [FindBy(Id = "customers")]
        public EntityTable<CompanyInfoShort> CompaniesShort = new EntityTable<CompanyInfoShort> { RowFrom = 2 };
    }

    public class ExampleFrame : Section
    {
        [FindsBy(How = How.LinkText, Using = "CSS")]
        public Link CSS;
    }
}
