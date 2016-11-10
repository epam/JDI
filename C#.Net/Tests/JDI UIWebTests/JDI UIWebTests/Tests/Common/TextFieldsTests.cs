using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using JDI_UIWebTests.Entities;
using System;
using JDI_Matchers.NUnit;

namespace JDI_UIWebTests.Tests.Common
{
    public class TextFieldsTests
    {
        private readonly string DEFAULT_TEXT = User.DefaultUser.Login;
        private const string CONTAINS = "ame";
        private const string REGEX = ".am.";
        private const string TO_ADD_TEXT = "text123!@#$%^&*()";
        private const string EXPECTED_TEXT = "text123!@#$%^&*()";
        private const string ELEMENT_TYPE = "TextField";

        [SetUp]
        public void SetUp()
        {
            Logger.Info("Navigating to Metals and Colors page.");
            ContactFormPage.Open();
            ContactFormPage.CheckTitle();
            ContactFormPage.IsOpened();
            ContactFormPage.FillAndSubmitForm(DEFAULT_TEXT, DEFAULT_TEXT + (new Random()).Next(), DEFAULT_TEXT + (new Random()).Next());
            Logger.Info("Setup method finished");
            Logger.Info("Start test: " + TestContext.CurrentContext.Test.Name);
        }


        [Test]
        public void InputTest()
        {
            ContactFormPage.NameField.Input(TO_ADD_TEXT);
            new Check().AreEquals(ContactFormPage.NameField.GetText, DEFAULT_TEXT + TO_ADD_TEXT);            
        }


        [Test]
        public void SendKeyTest()
        {
            ContactFormPage.NameField.SendKeys(TO_ADD_TEXT);
            new Check().AreEquals(ContactFormPage.NameField.GetText, DEFAULT_TEXT + TO_ADD_TEXT);            
        }

        [Test]
        public void NewInputTest()
        {
            ContactFormPage.NameField.NewInput(TO_ADD_TEXT);
            new Check().AreEquals(ContactFormPage.NameField.GetText, TO_ADD_TEXT);            
        }

        [Test]
        public void ClearTest()
        {
            ContactFormPage.NameField.Clear();
            new Check().AreEquals(ContactFormPage.NameField.GetText, "");            
        }

        [Test]
        public void MultiKeyTest()
        {
            foreach (char letter in TO_ADD_TEXT.ToCharArray())
            {
                ContactFormPage.NameField.SendKeys(letter.ToString());
            }
            new Check().AreEquals(ContactFormPage.NameField.GetText, DEFAULT_TEXT + TO_ADD_TEXT);            
        }

        //TO_DO
        /*
        [Test]
        public void FocusTest()
        {

        }
        */
    }
}
