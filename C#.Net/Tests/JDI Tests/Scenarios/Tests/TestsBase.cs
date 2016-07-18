using JDI_Commons;
using JDI_Tests.Scenarios.Page_Objects;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Elements.Composite;
using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_Web.Selenium.DriverFactory.WinProcUtils;
using static JDI_Web.Settings.WebSettings;

namespace JDI_Tests.Scenarios.Tests
{
    [SetUpFixture]
    public class TestsBase
    {
        Timer _timer = new Timer();

        [OneTimeSetUp]
        public void Init()
        {
            InitFromProperties();
            Logger.Info("Init test run");
            KillAllRunWebDrivers();

            if (!DriverFactory.HasDrivers())
                UseDriver(DriverTypes.Chrome);
            _timer = new Timer();
            
            WebSite.Init(typeof(EpamSite));
            EpamSite.HomePage.Open();
            Logger.Info("Run Tests");
        }

        [OneTimeTearDown]
        public void TestCleanup()
        {
            Logger.Info($@"
Test run finished.
Total test run time: {_timer.TimePassed.ToString(@"hh\:mm\:ss\.fff")}");
            KillAllRunWebDrivers();
        }
    }
    
}
