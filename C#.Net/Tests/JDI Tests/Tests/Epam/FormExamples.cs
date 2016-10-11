using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using JDI_Tests.Entities;
using JDI_Tests.Epam_UIObjects;
using JDI_Tests.Epam_UIObjects.DataProviders;
using NUnit.Framework;

namespace JDI_Tests.Tests.Epam
{
    [TestFixture]
    public class FormExamples
    {
        [SetUp]
        public void SetUp()
        {
            EpamSite.JobDescriptionPage.IsOpened();
        }

        [Test, TestCaseSource(typeof(AttendeesProvider), "Attendees")]
        public void FillFormExample(Attendee attendee)
        {
            EpamSite.JobDescriptionPage.AddCvForm.Submit(attendee);
            // It's not stable, GetAttribute() sometimes returnes all classes and sometimes only a part 'required '
            Assert.That(EpamSite.JobDescriptionPage.Captcha.GetAttribute("class"), Does.Contain("form-field-error"));
        }
    }
}
