using JDI_Tests.Entities;
using JDI_Tests.Enums;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using static JDI_Tests.Epam_UIObjects.EpamSite;

namespace JDI_Tests.Tests.Epam
{
    [TestClass]
    public class SmokeTestsMSTest
    {
        [TestMethod]
        public void CareerTest()
        {
            var attendee = new Attendee();
            HeaderMenu2D.HoverAndClick("SOLUTIONS>Product Development");
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
