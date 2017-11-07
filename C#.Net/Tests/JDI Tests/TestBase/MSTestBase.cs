using Epam.JDI.Core.Settings;
using JDI_Commons;
using JDI_Tests.Epam_UIObjects;
using JDI_Web.Settings;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_Tests.Epam_UIObjects.EpamSite;
using static JDI_Web.Selenium.DriverFactory.WinProcUtils;
using static JDI_Web.Selenium.Elements.Composite.WebSite;
using static JDI_Web.Settings.WebSettings;

namespace JDI_Tests.Tests.Epam
{
    [TestClass]
    public class MSTestBase
    {
        static Timer _timer = new Timer();

        [AssemblyInitialize]
        public static void TestsInit(TestContext context)
        {
            InitMsTestDefault();
            Logger.Info("Init test run");
            KillAllRunWebDrivers();
            _timer = new Timer();
            Init(typeof(EpamSite));
            HomePage.Open();
            Logger.Info("Run Tests");
        }

        [AssemblyCleanup]
        public static void TestCleanup()
        {
            Logger.Info($@"
Test run finished.
Total test run time: {_timer.TimePassed.ToString(@"hh\:mm\:ss\.fff")}");
            KillAllRunWebDrivers();
        }
    }
    
}
