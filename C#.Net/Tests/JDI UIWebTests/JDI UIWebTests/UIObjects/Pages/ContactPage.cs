using Epam.JDI.Core.Interfaces.Common;
using JDI_UIWebTests.UIObjects.Sections;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Composite;
using JDIWebTests.UIObjects.Sections;

namespace JDI_UIWebTests.UIObjects.Pages
{
    public class ContactPage:WebPage
    {
        [FindBy(Id = "Name")]
        public TextField NameField;

        [FindBy(Id = "LastName")]
        public ITextField LastNameField;

        [FindBy(Id = "Description")]
        public TextArea DescriptionField;

        [FindBy(XPath = "//*[text()='Submit']")]
        public IButton ContactSubmit;

        [FindBy(Css = ".epam-logo img")]
        public IImage LogoImage;

        [FindBy(Css = ".results")]
        public IText Result;

        [FindBy(Css = "main form")]
        public ContactForm ContactForm;

        [FindBy(Css = "main form")]
        public ContactFormTwoButtons ContactFormTwoButtons;

        public void FillFormWithoutSubmitting(string firstName, string secondName, string description)
        {
            FillForm(firstName, secondName, description);
        }

        public void FillAndSubmitForm(string firstName, string secondName, string description)
        {
            FillForm(firstName, secondName, description);
            ContactSubmit.Click();
        }


        private void FillForm(string firstName, string secondName, string description)
        {
            NameField.NewInput(firstName);
            LastNameField.NewInput(secondName);
            DescriptionField.NewInput(description);
        }
    }
}
