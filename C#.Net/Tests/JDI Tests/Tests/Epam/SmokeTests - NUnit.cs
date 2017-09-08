using System;
using JDI_Tests.Entities;
using JDI_Tests.Enums;
using NUnit.Framework;
using static JDI_Tests.Epam_UIObjects.EpamSite;

namespace JDI_Tests.Tests.Epam
{
    [TestFixture]
    public class SmokeTestsNUnit 
    {

        [Test]
        public void CareerTest()
        {
            var attendee = new Attendee();
            HomePage.IsOpened();
            HeaderMenu2D.HoverAndClick("SOLUTIONS>Product Development");
            ProductDevelopmentPage.CheckOpened();
            HeaderMenu.Select(Headers.CAREERS);
            CareerPage.CheckOpened();
            CareerPage.JobFilter.Search(attendee.Filter);
            JobListingPage.CheckOpened();
            //new Check("Table is not empty").isFalse(jobListingPage.jobsList::isEmpty);
            //new Timer().Wait(() => !JobListingPage.JobsList.Empty);
            JobListingPage.GetJobRowByName("QA Specialist");
            JobDescriptionPage.AddCvForm.Submit(attendee);
            //new Check("Captcha").Contains(() -> jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
        }
        
    }
}
