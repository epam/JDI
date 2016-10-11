using JDI_UIWebTests.Entities;
using JDIWebTests.UIObjects.Sections;
using System.Collections.Generic;
using static JDI_UIWebTests.Tests.Complex.CommonActionsData;
using static JDI_UIWebTests.Enums.Buttons;
using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;

namespace JDI_UIWebTests.Tests.Composite
{
    public class PageTests
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

    }
}
