using JDI_Core.Interfaces.Common;
using JDI_Core.Interfaces.Complex;
using JDI_Tests.Scenarios.Entities;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Composite;
using OpenQA.Selenium;

namespace JDI_Tests.Scenarios.Page_Objects.Sections
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
