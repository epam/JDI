﻿using Epam.Tests.Scenarios.Entities;
using NUnit.Framework;
using static Epam.Tests.Scenarios.Enums.HeaderMenuValues;
using static Epam.Tests.Scenarios.Page_Objects.EpamSite;

namespace Epam.Tests.Scenarios.Tests
{
    [TestFixture]
    public class SmokeTests
    {
        [SetUp]
        public void SetUp()
        {
            Init();
        }

        [Test]
        public void CareerTest()
        {
            var attendee = new Attendee();
            HeaderMenu.Select(CAREERS);
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
