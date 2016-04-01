using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Core.Interfaces.Complex;
using Epam.JDI.Tests.Aliases;
using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Composite;
using OpenQA.Selenium;

namespace Epam.Tests.TutorialTests.Career.PageObjects
{
    public class AddCVForm : Form<Attendee>
    {

        [FindBy(Css = "[placeholder='First Name']")]
        private ITextField name;
        [FindBy(Css = "[placeholder='Last Name']")]
        private ITextField lastName;
        [FindBy(Css = "[placeholder='Email']")]
        private ITextField email;
        [FindBy(Css = ".country-selection")]
        private IDropDown country = new Dropdown(
            By.CssSelector(".country-wrapper .arrow"),
            By.XPath("//*[contains(@id,'select-box-applicantCountry')]//li"));
        [FindBy(Css = ".city-selection")]
        private IDropDown city = new Dropdown(
          By.CssSelector(".city-wrapper .arrow"),
          By.XPath("//*[contains(@id,'select-box-applicantCity')]//li"));
        [FindBy(Css = ".file-upload")]
        private RFileInput cv;
        [FindBy(Css = ".comment-input")]
        private ITextArea comment;

        [FindBy(XPath = "//*[.='Submit']")]
        private IButton submit;
        [FindBy(XPath = "//*[.='Cancel']")]
        private IButton cancel;

    }
}