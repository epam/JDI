using JDI_Tests.Entities;
using JDI_Tests.Enums;
using NUnit.Framework;
using OpenQA.Selenium.Chrome;
using static JDI_Tests.Epam_UIObjects.EpamSite;

namespace JDI_Tests.Tests
{
    [TestFixture]
    public class SmokeTestsNUnit 
    {
        [Test]
        public void CareerTest()
        {
            var attendee = new Attendee();
            HomePage.IsOpened();
            HeaderMenu2D.HoverAndClick("SOLUTIONS|Product Development");
            ProductDevelopmentPage.CheckOpened();
            HeaderMenu.Select(Headers.CAREERS);
            CareerPage.CheckOpened();
            CareerPage.JobFilter.Search(attendee.Filter);
            JobListingPage.CheckOpened();
            //new Check("Table is not empty").isFalse(jobListingPage.jobsList::isEmpty);
            JobListingPage.GetJobRowByName("Senior QA Automation Engineer");
            JobDescriptionPage.AddCvForm.Submit(attendee);
            //new Check("Captcha").Contains(()->jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
        }
        
    }
}
