using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using JDI_UIWebTests.Entities;
using static JDI_UIWebTests.Tests.Complex.CommonActionsData;
using JDI_UIWebTests.UIObjects.Sections;

namespace JDI_UIWebTests.Tests.Composite
{
    public class FormTwoButtonsTests
    {
        private ContactFormTwoButtons _contactForm = ContactFormPage.ContactFormTwoButtons;

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
        public void SubmitSpecButtonStringTest()
        {
            _contactForm.Submit(Contact.DEFAULT_CONTACT, "calculate");
            CheckResult("Summary: 3");
        }
    }
}
