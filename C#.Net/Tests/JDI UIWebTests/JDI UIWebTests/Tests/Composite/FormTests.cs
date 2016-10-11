using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using JDI_UIWebTests.Entities;
using JDIWebTests.UIObjects.Sections;
using System.Collections.Generic;
using static JDI_UIWebTests.Tests.Complex.CommonActionsData;
using static JDI_UIWebTests.Enums.Buttons;

namespace JDI_UIWebTests.Tests.Composite
{
    public class FormTests
    {
        private ContactForm _contactForm = ContactFormPage.ContactForm;

        [SetUp]
        public void SetUp()
        {
            Logger.Info("Navigating to Contact page.");
            ContactFormPage.Open();
            ContactFormPage.CheckTitle();
            ContactFormPage.IsOpened();
            Logger.Info("Setup method finished");
            Logger.Info("Start test: " + TestContext.CurrentContext.Test.Name);
        }

        [Test]
        public void FillFormTest()
        {
            _contactForm.Fill(Contact.DEFAULT_CONTACT);            
            IList<string> filledFilds = _contactForm.GetFormValue();            
            //TO_DO replace widt JDI matcher
            Assert.True(filledFilds.ToString().Equals(Contact.DEFAULT_CONTACT.ToList().ToString()));            
        }

        [Test]
        public void SubmitTest()
        {
            _contactForm.Submit(Contact.DEFAULT_CONTACT);            
            checkResult(Contact.DEFAULT_CONTACT.ToString());
        }

        [Test]
        public void SubmitSpecButtonStringTest()
        {
            _contactForm.Submit(Contact.DEFAULT_CONTACT, "submit");
            checkResult(Contact.DEFAULT_CONTACT.ToString());
        }

        [Test]
        public void SubmitSpecButtonEnumTest()
        {
            _contactForm.Submit(Contact.DEFAULT_CONTACT, SUBMIT);
            checkResult(Contact.DEFAULT_CONTACT.ToString());
        }

        [Test]
        public void SubmitStringTest()
        {
            _contactForm.Submit(Contact.DEFAULT_CONTACT.FirstName);
            string s = string.Format("Summary: 3\r\nName: {0}",
                         Contact.DEFAULT_CONTACT.FirstName);
            checkResult(s);
        }

    }
}
