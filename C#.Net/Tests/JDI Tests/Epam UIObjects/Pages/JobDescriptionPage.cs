using Epam.JDI.Core.Interfaces.Base;
using JDI_Tests.Epam_UIObjects.Sections;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_Tests.Epam_UIObjects.Pages
{
    public class JobDescriptionPage : WebPage
    {
        [FindBy(Css = ".form-constructor")]
        public AddCVForm AddCvForm;

        [FindBy(Id = "captcha-input")]
        public IElement Captcha;
    }
}
