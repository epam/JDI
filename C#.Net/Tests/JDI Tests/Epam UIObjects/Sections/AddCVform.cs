﻿using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_Tests.Entities;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Composite;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace JDI_Tests.Epam_UIObjects.Sections
{
    public class AddCVForm : Form<Attendee>
    {
        [FindsBy(How = How.CssSelector, Using = "[placeholder='First Name']")]
        ITextField _name;
        [FindBy(Css = "[placeholder='Last Name']")]
        ITextField _lastName;
        [FindsBy(How = How.CssSelector, Using = "[placeholder='Email']")]
        ITextField _email;
        [FindsBy(How = How.CssSelector, Using = ".country-selection")]
        IDropDown _country = new Dropdown(By.CssSelector(".country-wrapper .arrow"),
                By.XPath("*root*//*[contains(@id,'select-box-applicantCountry')]//li"));
        [FindsBy(How = How.CssSelector, Using = ".city-selection")]
        IDropDown _city = new Dropdown(By.CssSelector(".city-wrapper .arrow"),
                By.XPath("*root*//*[contains(@id,'select-box-applicantCity')]//li"));
        [FindsBy(How = How.CssSelector, Using = ".comment-input")]
        ITextArea _comment;

        [FindBy(XPath = "//*[.='Submit']")]
        IButton _submit;
        [FindBy(XPath = "//*[.='Cancel']")]
        IButton _cancel;
    }
}
