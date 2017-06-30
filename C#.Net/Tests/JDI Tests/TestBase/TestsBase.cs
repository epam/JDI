using Epam.JDI.Core.Settings;
using JDI_Commons;
using JDI_Tests.Epam_UIObjects;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Elements.Composite;
using JDI_Web.Settings;
using NUnit.Framework;

namespace JDI_Tests.Tests.Epam
{
    [SetUpFixture]
    public class TestsBase
    {
        Timer _timer = new Timer();
        
        [OneTimeSetUp]
        public void Init()
        {
            WebSettings.InitNUnitDefault();
            JDISettings.Logger.Info("Init test run");
            WinProcUtils.KillAllRunWebDrivers();
            _timer = new Timer();
            WebSite.Init(typeof(EpamSite));
            EpamSite.HomePage.Open();
            JDISettings.Logger.Info("Run Tests");
        }

        [OneTimeTearDown]
        public void TestCleanup()
        {
            JDISettings.Logger.Info($@"
Test run finished.
Total test run time: {_timer.TimePassed.ToString(@"hh\:mm\:ss\.fff")}");
            WinProcUtils.KillAllRunWebDrivers();
        }
    }
    
}
