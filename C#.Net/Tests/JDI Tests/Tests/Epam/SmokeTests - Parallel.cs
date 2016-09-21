using Epam.JDI.Core.Settings;
using JDI_Tests.Entities;
using JDI_Tests.Enums;
using NUnit.Framework;
using JDI_Tests.Epam_UIObjects;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Settings;

namespace JDI_Tests.ParallelTests
{
    [TestFixture]
    public class SmokeTestsParallel
    {
        private EpamSiteP Site;
        [SetUp]
        public void SetUp()
        {
            WebSettings.InitNUnitDefault();
            JDISettings.Logger.Info("Init test run");
            WinProcUtils.KillAllRunWebDrivers();
            Site = new EpamSiteP().Init<EpamSiteP>();
            Site.HomePage.Open();
            JDISettings.Logger.Info("Run Tests");
        }
        [OneTimeTearDown]
        public void TestCleanup()
        {
            WinProcUtils.KillAllRunWebDrivers();
        }

        [Test]
        public void CareerTest()
        {
            var attendee = new Attendee();
            Site.HomePage.IsOpened();
            Site.HeaderMenu2D.HoverAndClick("SOLUTIONS>Product Development");
            Site.ProductDevelopmentPage.CheckOpened();
            Site.HeaderMenu.Select(Headers.CAREERS);
            Site.CareerPage.CheckOpened();
            Site.CareerPage.JobFilter.Search(attendee.Filter);
            Site.JobListingPage.CheckOpened();
            //new Check("Table is not empty").isFalse(jobListingPage.jobsList::isEmpty);
            Site.JobListingPage.GetJobRowByName("Senior QA Automation Engineer");
            Site.JobDescriptionPage.AddCvForm.Submit(attendee);
            //new Check("Captcha").Contains(()->jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
        }
        
    }
}
