
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Web.Attributes;

namespace Epam.Tests.TutorialTests.Career.PageObjects
{
    public class JobDescriptionPage : JDI.Web.Selenium.Elements.Composite.WebPage
    {
        [FindBy(Css = ".form-constructor")]
        public AddCVForm addCVForm;

        [FindBy(Id = "captcha-input")]
        public IElement captcha;

    }
}