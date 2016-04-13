using Epam.JDI.Core.Settings;
using Epam.JDI.Web.Selenium.Elements.Composite;
using Epam.Tests.TutorialTests.Career.PageObjects;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Epam.Tests.TutorialTests.Career.Tests
{
    [TestClass]
    public class TestsBase
    {

        [ClassInitialize()]
        public static void setUp()
        {
            WebSite.Init(typeof(EpamSite));
            EpamSite.homePage.Open();
            JDISettings.Logger.Info("Run Tests");
        }

    }
}