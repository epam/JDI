using Epam.JDI.Core.Attributes;
using Epam.JDI.Core.Interfaces.Common;
using JDI_UIWebTests.Entities;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Composite;

namespace JDIWebTests.UIObjects.Sections
{
    public class ContactForm : Form<Contact>
    {
        [FindBy(Css = "input#Name")]
        [Name("Name")]
        public ITextField NameField;

        [FindBy(Css = "input#LastName")]
        [Name("Last Name")]
        public ITextField LastNameField;

        [FindBy(Css = "textarea#Description")]
        [Name("Description")]
        public ITextArea DescriptionField;

        [FindBy(XPath = "//button[@type='submit' and contains(., 'Submit')]")]
        public IButton SubmitButton;
    }
}
