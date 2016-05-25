using System.Collections.Generic;
using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Core.Interfaces.Complex;
using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Common;
using Epam.JDI.Web.Selenium.Elements.Complex;
using Epam.JDI.Web.Selenium.Elements.Composite;
using Epam.Tests.Scenarios.Entities;
using OpenQA.Selenium;

namespace Epam.Tests.Scenarios.Page_Objects.Sections
{
    public class AboutCategoriesSection : Form<AboutCategoriesSection>
    {
        [FindBy(Css = "img[alt*=\"Company\"]")]
        public Image Company;

        [FindBy(Css = "img[alt*=\"Newsroom\"]")]
        public Image Newsroom;

        [FindBy(Css = "img[alt*=\"Investors\"]")]
        public Image Investors;
    }
}
