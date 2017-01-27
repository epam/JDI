using Epam.JDI.Core.Logging;
using Epam.JDI.Core.Settings;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Settings;
using NUnit.Framework;
using JDI_Web.Selenium.Elements.Composite;
using JDI_UIWebTests.UIObjects;
using JDI_UIWebTests.Entities;

namespace JDI_UIWebTests.Tests
{
    [SetUpFixture]
    public class TestBase
    {
        [OneTimeSetUp]
        protected void SetUp()
        {            
            WebSettings.InitNUnitDefault();     
            var logger = JDISettings.Logger;
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
