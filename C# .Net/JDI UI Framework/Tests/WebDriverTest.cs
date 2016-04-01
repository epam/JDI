using Epam.JDI.Web.Selenium.DriverFactory;
using Epam.Properties;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.IE;

namespace Epam.Tests
{
    /// <summary>
    /// Summary description for UnitTest1
    /// </summary>
    [TestClass]
    public class WebDriverUnitTests
    {

        public TestContext TestContext { get; set; }

        [TestMethod]
        public void GetDefaultDriverTest()
        {
            WebDriverFactory f = new WebDriverFactory();
            f.GetDriver();
        }

        [TestMethod]
        public void GetChromeDriverTest()
        {
            WebDriverFactory f = new WebDriverFactory();
            f.RegisterDriver(DriverTypes.Chrome);
            f.GetDriver();
        }

        [TestMethod]
        public void GetIEDriverTest()
        {
            WebDriverFactory f = new WebDriverFactory();
            f.RegisterDriver("ie");
            f.GetDriver();
        }

        [TestMethod]
        public void GetFirefoxDriverTest()
        {
            WebDriverFactory f = new WebDriverFactory();
            f.RegisterDriver("firefox");
            f.GetDriver();
        }

        [TestMethod]
        public void KillAllDriversTest()
        {
            IWebDriver driver1 = new ChromeDriver(@"C:\Selenium");
            IWebDriver driver2 = new FirefoxDriver();
            IWebDriver driver3 = new InternetExplorerDriver(@"C:\Selenium");
        }

        [TestMethod]
        public void InitFromPropertys()
        {
            WebDriverFactory f = new WebDriverFactory();
            f.RegisterDriver(Settings.Default.driver);
            f.GetDriver();
        }

        [TestMethod]
        public void GetRemoteDriverTest()
        {
            WebDriverFactory f = new WebDriverFactory {RunType = RunTypes.Remote};
            f.RegisterDriver(DriverTypes.IE);
            f.GetDriver();
        }

        [TestCleanup]
        public void TearDown()
        {
            WebDriverUtils.KillAllRunWebDrivers();
        }
    }
}