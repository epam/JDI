﻿using Epam.JDI.Core.Interfaces.Common;
using JDI_UIWebTests.Entities;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Composite;
using System.Collections.Generic;


namespace JDI_UIWebTests.UIObjects.Sections
{
    public class ContactFormTwoButtons : Form<Contact>
    {
        [FindBy(Id = "Name")]
        public new TextField Name;

        [FindBy(Id = "LastName")]
        public TextField LastName;

        [FindBy(Id = "Description")]
        public TextArea Description;

        [FindBy(XPath = "//*[text()='Submit']")]
        public new IButton Submit;

        [FindBy(XPath = ".//a[@class='ui-slider-handle ui-state-default ui-corner-all' and position()=1]")]
        public Link FirstRoller;

        [FindBy(XPath = ".//a[@class='ui-slider-handle ui-state-default ui-corner-all' and position()=2]")]
        public Link SecondRoller;

        public List<string> GetFormValue()
        {
            var fields = new List<string>
            {
                Name.GetText,
                LastName.GetText,
                Description.GetText
            };
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
