﻿using System.Collections.Generic;
using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Common;
using Epam.JDI.Web.Selenium.Elements.Complex;
using Epam.Tests.Scenarios.Enums;
using Epam.Tests.Scenarios.Page_Objects.Pages;
using static Epam.JDI.Web.Selenium.Elements.Composite.CheckPageTypes;

namespace Epam.Tests.Scenarios.Page_Objects
{
    using Tests;

    [Site(Domain = "https://www.epam.com")]
    public class EpamSite
    {
        public static void Init()
        {
            TestsBase.Init();
        }

        [Page(Url = "/", Title = "EPAM | Software Product Development Services")]
        public static HomePage HomePage;

        [Page(Url = "/careers", Title = "Careers")]
        public static CareerPage CareerPage;
        [Page(Title = "EPAM Software Product Development Services", UrlCheckType = None)]
        public static CareerPage CareerPageTest;

        [Page(Url = "/careers/job-listings", Title = "Job Listings", UrlCheckType = Contain, TitleCheckType = Contain)]
        public static JobListingPage JobListingPage;

        [Page(Url = ".*/careers/job-listings/job\\.\\d*#apply", UrlCheckType = Match)]
        public static JobDescriptionPage JobDescriptionPage;

        [FindBy(Css = ".tile-menu>li>a")]
        public static Menu<HeaderMenuValues> HeaderMenu;

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
