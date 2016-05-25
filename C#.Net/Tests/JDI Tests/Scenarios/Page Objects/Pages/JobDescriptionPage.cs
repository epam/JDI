using JDI_Core.Interfaces.Base;
using JDI_Tests.Scenarios.Page_Objects.Sections;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_Tests.Scenarios.Page_Objects.Pages
{
    public class JobDescriptionPage : WebPage
    {
        [FindBy(Css = ".form-constructor")]
        public AddCVForm AddCvForm;

        [FindBy(Id = "captcha-input")]
        public IElement Captcha;
    }
}
