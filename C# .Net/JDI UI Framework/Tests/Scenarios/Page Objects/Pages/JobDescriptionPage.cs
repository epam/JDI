using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Composite;
using Epam.Tests.Scenarios.Page_Objects.Sections;

namespace Epam.Tests.Scenarios.Page_Objects.Pages
{
    public class JobDescriptionPage : WebPage
    {
        [FindBy(Css = ".form-constructor")]
        public AddCVForm AddCvForm;

        [FindBy(Id = "captcha-input")]
        public IElement Captcha;
    }
}
