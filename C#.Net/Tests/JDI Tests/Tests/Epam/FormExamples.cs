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

        [Test]
        public void FillFormExample([ValueSource(typeof(AttendeesProvider), "Attendees")] Attendee attendee)
        {
            EpamSite.JobDescriptionPage.AddCvForm.Submit(attendee);
            // It's not stable, GetAttribute() sometimes returnes all classes and sometimes only a first part 'required '
            Assert.That(EpamSite.JobDescriptionPage.Captcha.GetAttribute("class"), Does.Contain("form-field-error"));
        }
    }
}
