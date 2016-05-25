using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Core.Interfaces.Complex;
using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Complex;
using Epam.JDI.Web.Selenium.Elements.Composite;
using Epam.Tests.Scenarios.Entities;
using OpenQA.Selenium;

namespace Epam.Tests.Scenarios.Page_Objects.Sections
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
