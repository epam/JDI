using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_UIWebTests.UIObjects.Pages
{
    public class HomePage:WebPage
    {
        [FindBy(Css = ".main-txt")]
        public Text Text;

        [FindBy(Css = ".epam-logo img")]
        public IImage LogoImage;

        [FindBy(Css = "[class=icon-search]")]
        public IButton OpenSearchButton;
    }
}
