using System.Collections.Generic;
using JDI_Tests.Scenarios.Enums;
using JDI_Tests.Scenarios.Page_Objects.Pages;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Complex;
using static JDI_Web.Selenium.Elements.Composite.CheckPageTypes;

namespace JDI_Tests.Scenarios.Page_Objects
{
    [Site(Domain = "https://www.epam.com")]
    public class EpamSite
    {
        [Page(Url = "/", Title = "EPAM | Software Product Development Services")]
        public static HomePage HomePage;

        [Page(Url = "/about", Title = "About")]
        public static AboutPage AboutPage;

        [Page(Url = "/contact", Title = "Contact")]
        public static ContactPage ContactPage;

        [Page(Url = "/request-information", Title = "Request Information")]
        public static RequestInformationPage RequestInformationPage;

        [Page(Url = "/about/company", Title = "Company")]
        public static AboutCompanyPage AboutCompanyPage;

        [Page(Url = "/careers", Title = "Careers")]
        public static CareerPage CareerPage;

        [Page(Title = "EPAM Software Product Development Services", UrlCheckType = None)]
        public static CareerPage CareerPageTest;

        [Page(Url = "/careers/job-listings", Title = "Job Listings", UrlCheckType = Contain, TitleCheckType = Contain)]
        public static JobListingPage JobListingPage;

        [Page(Url = ".*/careers/job-listings/job\\.\\d*#apply", UrlCheckType = Match)]
        public static JobDescriptionPage JobDescriptionPage;

        [FindBy(Css = ".tile-menu>li>a")]
        public static Menu<Headers> HeaderMenu;

        [FindBy(Css = ".tile-menu>li>a")]
        public static IList<Label> ListMenu;
        /*
        [FindBy(Css = ".tile-menu .submenu a")]
        public static Menu<HeaderSolutionsMenu> HeaderSolutionsMenu = new Menu<HeaderSolutionsMenu> {
            protected new void SelectNameAction(string name)
            {
                Actions action = new Actions(WebSettings.WebDriver);
                IWebElement el = WebSettings.WebDriver.findElements(By.cssSelector(".tile-menu a")).get(0);
                action.moveToElement(el).build().perform();
                super.selectAction(name);
            }
        };*/
    }
}
