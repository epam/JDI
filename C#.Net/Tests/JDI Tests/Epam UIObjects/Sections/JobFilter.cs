using System.Collections.Generic;
using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_Tests.Entities;
using JDI_Tests.Epam_UIObjects.Elements;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Composite;
using OpenQA.Selenium;

namespace JDI_Tests.Epam_UIObjects.Sections
{
    public class JobFilter : Form<JobSearchFilter>
    {
        [FindBy(ClassName = "job-search-input")]
        public ITextField Keywords;
        public IDropDown Category = new Dropdown(
            By.ClassName("multi-select-filter"), 
            By.ClassName("blue-checkbox-label"));

        [FindBy(ClassName = "career-location-box")]
        public IDropDown City;
        
        public TreeDropdown Location = new TreeDropdown(
                By.ClassName("career-location-box"),
                new List<By> {
                    By.CssSelector(".location-dropdown .optgroup"),
                    By.XPath("//..//li")
                });

        [FindBy(ClassName = "job-search-button")]
        public IButton SelectButton;
    }
}
