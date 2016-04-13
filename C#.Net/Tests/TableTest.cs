using Epam.JDI.Web.Selenium.Elements.Complex.table;
using NUnit.Framework;
using OpenQA.Selenium;
using static Epam.JDI.Web.Selenium.DriverFactory.WebDriverUtils;

namespace Epam.Tests
{
    [TestFixture]
    public class TableTests
    {
        
        public TestContext TestContext { get; set; }
        private IWebDriver Driver { set; get; }
        private Table Table { get; set; }

        [TestFixtureSetUp]
        public void MyTestInitialize()
        {
            //Driver = new WebDriverFactory().GetDriver();
            //Driver.Url = "http://ecse00100176.epam.com/";
            //Cookie login = new Cookie("authUser", "true", "ecse00100176.epam.com", "/", null);
            //Driver.Manage().Cookies.AddCookie(login);
            //Driver.Url = "http://ecse00100176.epam.com/page3.htm";
        }

        [Test]
        public void GetDefaultDriverTest()
        {
            Table = new Table(By.CssSelector(".uui-table"));
        }

        [TestFixtureTearDown]
        public void TearDown()
        {
            KillAllRunWebDrivers();
        }
    }
}