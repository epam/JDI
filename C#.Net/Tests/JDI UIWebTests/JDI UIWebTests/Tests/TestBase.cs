using Epam.JDI.Core.Logging;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Settings;
using NUnit.Framework;
using JDI_Web.Selenium.Elements.Composite;
using JDI_UIWebTests.UIObjects;
using JDI_UIWebTests.Entities;
using OpenQA.Selenium;

namespace JDI_UIWebTests.Tests
{
    [SetUpFixture]
    public class TestBase
    {
        public IWebDriver Driver;

        [OneTimeSetUp]
        protected void SetUp()
        {
            // Initialize logger (NLog)
            WebSettings.InitNUnitDefault();
            this.Driver = WebSettings.WebDriver;
            ILogger logger = WebSettings.Logger;
            logger.Info("Init test run...");
            WinProcUtils.KillAllRunWebDrivers();
            WebSite.Init(typeof(TestSite));
            TestSite.HomePage.Open();
            TestSite.LoginForm.Submit(User.DefaultUser);            
            logger.Info("Run test...");
        }

        [OneTimeTearDown]
        protected void TearDown()
        {
            // Some log outputs
            WinProcUtils.KillAllRunWebDrivers();
        }
    }
}
