using System;
using Epam.JDI.Commons;
using Epam.JDI.Web.Selenium.Elements.Composite;
using Epam.Tests.Scenarios.Page_Objects;
using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static Epam.JDI.Web.Selenium.DriverFactory.DriverTypes;
using static Epam.JDI.Web.Selenium.DriverFactory.WebDriverUtils;
using static Epam.JDI.Web.Settings.WebSettings;

namespace Epam.Tests.Scenarios.Tests
{
    public class TestsBase
    {
        protected static Timer Timer;
        public static TimeSpan TestRunTime => Timer.TimePassed;

        [SetUp]
        public void Init()
        {
            InitFromProperties();
            Logger.Info("Init test run");
            KillAllRunWebDrivers();

            if (!DriverFactory.HasDrivers())
            {
                UseDriver(Chrome);
            }
            Timer = new Timer();
            
            WebSite.Init(typeof(EpamSite));
            EpamSite.HomePage.Open();
            Logger.Info("Run Tests");
        }

        [TearDown]
        public void TestCleanup()
        {
            Logger.Info($@"
Test run finished.
Total test run time: {TestRunTime.ToString(@"hh\:mm\:ss\.fff")}");
            KillAllRunWebDrivers();
        }
    }
    
}
