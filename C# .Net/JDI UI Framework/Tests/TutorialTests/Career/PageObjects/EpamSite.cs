using Epam.JDI.Core.Interfaces.Complex;
using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Common;
using System.Collections.Generic;
using Epam.JDI.Web.Selenium.Elements.Composite;
using Epam.Tests.TutorialTests.Dummies.JDI;
using System;

namespace Epam.Tests.TutorialTests.Career.PageObjects
{
    [Site(Domain = "https://www.epam.com")]
    public class EpamSite : WebSite
    {
        [Page(Url = "/", Title = "EPAM | Software Product Development Services")]
        public static HomePage homePage;
        [Page(Url = "/careers", Title = "Careers")]
        public static CareerPage careerPage;
        [Page(Url = "/careers/job-listings", Title = "Job Listings",
            UrlCheckType = CheckPageTypes.Contain, TitleCheckType = CheckPageTypes.Contain)]
        public static JobListingPage jobListingPage;
        [Page(Url = ".*/careers/job-listings/job\\.\\d*#apply", UrlCheckType = CheckPageTypes.Match)]
        public static JobDescriptionPage jobDescriptionPage;
        [FindBy(Css = ".tile-menu>li>a")]
        public static IMenu<HeaderMenu> headerMenu;
        [FindBy(Css = ".tile-menu>li>a")]
        public static List<Label> listMenu;
        [FindBy(Css = ".tile-menu .submenu a")]
        public static Menu<HeaderSolutionsMenu> headerSolutionsMenu = new Menu<HeaderSolutionsMenu>()
        {
            SelectAction = (string name) => {
                //    Actions action = new Actions(getDriver());
                //    WebElement el = getDriver().findElements(By.cssSelector(".tile-menu a")).get(0);
                //    action.moveToElement(el).build().perform();
                //    super.selectAction(name);
            },
        };
    }
};


