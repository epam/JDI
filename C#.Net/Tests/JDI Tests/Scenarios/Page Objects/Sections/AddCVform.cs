using JDI_Core.Interfaces.Common;
using JDI_Core.Interfaces.Complex;
using JDI_Tests.Scenarios.Entities;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Composite;
using OpenQA.Selenium;

namespace JDI_Tests.Scenarios.Page_Objects.Sections
{
    public class AddCVForm : Form<Attendee>
    {
        [FindBy(Css = "[placeholder='First Name']")]
        ITextField _name;
        [FindBy(Css = "[placeholder='Last Name']")]
        ITextField _lastName;
        [FindBy(Css = "[placeholder='Email']")]
        ITextField _email;
        [FindBy(Css = ".country-selection")]
        IDropDown _country = new Dropdown(By.CssSelector(".country-wrapper .arrow"),
                By.XPath("*root*//*[contains(@id,'select-box-applicantCountry')]//li"));
        [FindBy(Css = ".city-selection")]
        IDropDown _city = new Dropdown(By.CssSelector(".city-wrapper .arrow"),
                By.XPath("*root*//*[contains(@id,'select-box-applicantCity')]//li"));
        [FindBy(Css = ".comment-input")]
        ITextArea _comment;

        [FindBy(XPath = "//*[.='Submit']")]
        IButton _submit;
        [FindBy(XPath = "//*[.='Cancel']")]
        IButton _cancel;
    }
}
