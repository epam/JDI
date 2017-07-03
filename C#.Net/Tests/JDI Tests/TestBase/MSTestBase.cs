using Epam.JDI.Core.Settings;
using JDI_Commons;
using JDI_Tests.Epam_UIObjects;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Elements.Composite;
using JDI_Web.Settings;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace JDI_Tests.Tests.Epam
{
    [TestClass]
    public class MSTestBase
    {
        static Timer _timer = new Timer();

        [AssemblyInitialize]
        public static void Init(TestContext context)
        {
            WebSettings.InitMsTestDefault();
            JDISettings.Logger.Info("Init test run");
            WinProcUtils.KillAllRunWebDrivers();
            _timer = new Timer();
            WebSite.Init(typeof(EpamSite));
            EpamSite.HomePage.Open();
            JDISettings.Logger.Info("Run Tests");
        }

        [AssemblyCleanup]
        public static void TestCleanup()
        {
            JDISettings.Logger.Info($@"
Test run finished.
Total test run time: {_timer.TimePassed.ToString(@"hh\:mm\:ss\.fff")}");
            WinProcUtils.KillAllRunWebDrivers();
        }
    }
    
}
