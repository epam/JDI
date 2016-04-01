using System.Collections.Generic;
using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Core.Interfaces.Complex;
using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Complex;
using Epam.JDI.Web.Selenium.Elements.Composite;
using Epam.Tests.Scenarios.Entities;
using OpenQA.Selenium;

namespace Epam.Tests.Scenarios.Page_Objects.Sections
{
    public class JobFilter : Form<JobSearchFilter>
    {
        [FindBy(ClassName = "job-search-input")]
        public ITextField Keywords;
        public IDropDown Category =
                new Dropdown(By.ClassName("multi-select-filter"), By.ClassName("blue-checkbox-label"));

        [FindBy(ClassName = "career-location-box")]
        public IDropDown City;


        [FindBy(ClassName = "job-search-button")]
        public IButton SelectButton;
    }
}
