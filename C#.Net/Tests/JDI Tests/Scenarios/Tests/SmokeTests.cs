using JDI_Tests.Scenarios.Entities;
using JDI_Tests.Scenarios.Enums;
using JDI_Tests.Scenarios.Page_Objects;
using NUnit.Framework;

namespace JDI_Tests.Scenarios.Tests
{
    [TestFixture]
    public class SmokeTests : TestsBase
    {
        [Test]
        public void CareerTest()
        {
            var attendee = new Attendee();
            EpamSite.HeaderMenu.Select(Headers.CAREERS);
            EpamSite.CareerPage.CheckOpened();
            EpamSite.CareerPage.JobFilter.Search(attendee.Filter);
            EpamSite.JobListingPage.CheckOpened();
            //new Check("Table is not empty").isFalse(jobListingPage.jobsList::isEmpty);
            EpamSite.JobListingPage.GetJobRowByName("Senior QA Automation Engineer");
            EpamSite.JobDescriptionPage.AddCvForm.Submit(attendee);
            //new Check("Captcha").Contains(()->jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
        }
    }
}
