using Epam.JDI.Core.Interfaces.Common;
using JDI_UIWebTests.Entities;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Composite;
using OpenQA.Selenium;
using System.Collections.Generic;


namespace JDI_UIWebTests.UIObjects.Sections
{
    public class ContactFormTwoButtons:Form<Contact>
    {
        [FindBy(Id = "Name")]
        public TextField Name;

        [FindBy(Id = "LastName")]
        public TextField LastName;

        [FindBy(Id = "Description")]
        public TextArea Description;

        [FindBy(XPath = "//*[text()='Submit']")]
        public IButton Submit;

        [FindBy(XPath = ".//a[@class='ui-slider-handle ui-state-default ui-corner-all' and position()=1]")]
        public Link FirstRoller;

        [FindBy(XPath = ".//a[@class='ui-slider-handle ui-state-default ui-corner-all' and position()=2]")]
        public Link SecondRoller;

        private IJavaScriptExecutor javaScriptExecutor;

        public List<string> GetFormValue()
        {
            List<string> fields = new List<string>();
            fields.Add(Name.GetText);
            fields.Add(LastName.GetText);
            fields.Add(Description.GetText);
            return fields;
        }

        public void FillForm(Contact contact)
        {
            Name.NewInput(contact.FirstName);
            LastName.NewInput(contact.LastName);
            Description.NewInput(contact.Description);            
        }  
    }
}
