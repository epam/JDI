using System.Collections.Generic;
using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Base;
using Epam.JDI.Web.Selenium.Elements.Common;
using Epam.JDI.Web.Selenium.Elements.Composite;
using Epam.Tests.Scenarios.Enums;
using Epam.Tests.Scenarios.Page_Objects.Sections;

namespace Epam.Tests.Scenarios.Page_Objects.Pages
{
    public class AboutPage : WebPage
    {
        [FindBy(ClassName = "font-size-26")]
        public Textbox AboutText;

        [FindBy(ClassName = "acs-commons-resp-colctrl-row")]
        public AboutCategoriesSection AboutCategoriesSection;
    }
}
