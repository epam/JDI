using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Composite;
using Epam.Tests.Scenarios.Entities;

namespace Epam.Tests.Scenarios.Page_Objects.Sections
{
    public class AddCVForm : Form<Attendee>
    {
        [FindBy(Css = "[placeholder='First Name']")]
        private ITextField _name;
        [FindBy(Css = "[placeholder='Last Name']")]
        private ITextField _lastName;
        [FindBy(Css = "[placeholder='Email']")]
        private ITextField _email;
        /*[FindBy(Css = ".country-selection")]
        private IDropDown country = new Dropdown(By.CssSelector(".country-wrapper .arrow"),
                By.XPath("//*[contains(@id,'select-box-applicantCountry')]//li"));
        [FindBy(Css = ".city-selection")]
        private IDropDown city = new Dropdown(By.CssSelector(".city-wrapper .arrow"),
                By.XPath("//*[contains(@id,'select-box-applicantCity')]//li"));*/
        [FindBy(Css = ".comment-input")]
        private ITextArea _comment;

        [FindBy(XPath = "//*[.='Submit']")]
        private IButton _submit;
        [FindBy(XPath = "//*[.='Cancel']")]
        private IButton _cancel;
    }
}
