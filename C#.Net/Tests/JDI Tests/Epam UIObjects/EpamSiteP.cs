using System.Collections.Generic;
using JDI_Tests.Enums;
using JDI_Tests.Epam_UIObjects.Pages;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Composite;
using OpenQA.Selenium;

namespace JDI_Tests.Epam_UIObjects
{
    [Site(Domain = "https://www.epam.com")]
    public class EpamSiteP : WebSite
    {
        [Page(Url = "/", Title = "EPAM | Software Product Development Services")]
        public HomePage HomePage;

        [Page(Url = "/about", Title = "About")]
        public AboutPage AboutPage;

        [Page(Url = "/contact", Title = "Contact")]
        public ContactPage ContactPage;

        [Page(Url = "/request-information", Title = "Request Information")]
        public RequestInformationPage RequestInformationPage;

        [Page(Url = "/about/company", Title = "Company")]
        public AboutCompanyPage AboutCompanyPage;

        [Page(Url = "/careers", Title = "Careers")]
        public CareerPage CareerPage;

        [Page(Url = "/solutions/core-engineering/product-development")]
        public ProductDevelopmentPage ProductDevelopmentPage;

        [Page(Title = "EPAM Software Product Development Services", UrlCheckType = CheckPageTypes.None)]
        public CareerPage CareerPageTest;

        [Page(Url = "/careers/job-listings", Title = "Job Listings", 
            UrlCheckType = CheckPageTypes.Contains, TitleCheckType = CheckPageTypes.Equal)]
        public JobListingPage JobListingPage;

        [Page(Url = ".*/careers/job-listings/job\\.\\d*#apply", UrlCheckType = CheckPageTypes.Match)]
        public JobDescriptionPage JobDescriptionPage;

        [FindBy(Css = ".tile-menu>li>a")]
        public Menu<Headers> HeaderMenu;
        public Menu<Headers> HeaderMenu2D = new Menu<Headers>(
            new List<By> {
                By.CssSelector(".tile-menu>li>a"),
                By.XPath("//*[@class='tile-menu']//*[@href='/solutions']//..//li")
            }
        );

        [FindBy(Css = ".tile-menu>li>a")]
        public IList<Label> ListMenu;
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
