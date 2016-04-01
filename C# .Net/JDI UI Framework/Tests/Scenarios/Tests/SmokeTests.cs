
﻿using Epam.Tests.Scenarios.Entities;
﻿using Epam.JDI.Web.Selenium.Elements.Complex;
using Epam.Tests.Scenarios.Entities;
using Epam.Tests.Scenarios.Enums;
using NUnit.Framework;
using static Epam.Tests.Scenarios.Page_Objects.EpamSite;

namespace Epam.Tests.Scenarios.Tests
{
    [TestFixture]
    public class SmokeTests
    {
        [Test]
        public void CareerTest()
        {
            var attendee = new Attendee();
            HeaderMenu.Select(HeaderMenuValues.CAREERS);
            CareerPage.CheckOpened();
            CareerPage.JobFilter.Search(attendee.Filter);
            JobListingPage.CheckOpened();
            /*
            new Check("Table is not empty").isFalse(jobListingPage.jobsList::isEmpty);
            jobListingPage.getJobRowByName("Senior QA Automation Engineer");
            JobDescriptionPage.AddCVForm.submit(Attendee);
            new Check("Captcha").Contains(()->jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
            */
        }
    }
}
