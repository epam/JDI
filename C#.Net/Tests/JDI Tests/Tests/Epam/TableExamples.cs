﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using JDI_Tests.Epam_UIObjects;
using JDI_Web.Selenium.Elements.Complex.Table;
using NUnit.Framework;
using RestSharp.Extensions;
using static JDI_Tests.Epam_UIObjects.EpamSite;

namespace JDI_Tests.Tests.Epam
{
    [TestFixture]
    public class TableExamples
    {
        [SetUp]
        public void SetUp()
        {
            JobListingPage.IsOpened();
            Assert.That(JobListingPage.JobsList.Empty, Is.False);
        }

        [Test]
        public void GetTableInfoExample()
        {
            Assert.That(JobListingPage.JobsList.Columns.Count, Is.EqualTo(4));
            Assert.That(JobListingPage.JobsList.Rows().Count, Is.EqualTo(2));
            string jobListValues = "||X||Title|Type|Location|Apply||\n" +
                                   "||1||QA Specialist|Software Test Engineering|St-Petersburg, Russia|Apply||\n" +
                                   "||2||Senior QA Automation Engineer|Software Test Engineering|St-Petersburg, Russia|Apply||";
            Assert.That(JobListingPage.JobsList.Value, Is.EqualTo(jobListValues));
        }

        [Test]
        public void SearchInTableExample()
        {
            JobListingPage.JobsList.Row("Senior QA Automation Engineer", Column.column("Title")).Apply.Click();
             JobDescriptionPage.CheckOpened();
        }

        [Test]
        public void SearchByMultiCriteriaInTableExample()
        {
            var firstRow = JobListingPage.JobsList.Rows(
                "Title=Senior QA Automation Engineer",
                "Type=Software Test Engineering").First();
            Assert.That(firstRow.Location.GetText, Is.EqualTo("St-Petersburg, Russia"));
        }
    }
}
