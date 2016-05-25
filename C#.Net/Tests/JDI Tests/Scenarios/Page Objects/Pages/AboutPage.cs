using System.Collections.Generic;
using JDI_Tests.Scenarios.Page_Objects.Sections;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Base;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_Tests.Scenarios.Page_Objects.Pages
{
    public class AboutPage : WebPage
    {
        [FindBy(ClassName = "job-search")]
        public JobFilter JobFilter;

        [FindBy(Css = ".tile-menu>li>a")]
        public IList<Label> ListMenu;

        [FindBy(Css = ".job-search-title")]
        public Textbox JobSearchTitle;

        [FindBy(XPath = "//div[@class='footer - content']/div/a[@href='/about/investors']")]
        public Link InvestorLinkFooter;

        [FindBy(Css = ".copyright>p")]
        public Textbox CopyRight;

        [FindBy(XPath = "//ul[contains(@class,'header-menu-crumb')]/*[1]/a")]
        public Link HeaderMenuCrumbs;

        [FindBy(XPath = "//ul[contains(@class,'header-menu-crumb')]/*[2]/a")]
        public Link HeaderMenuCrumbs2;

        [FindBy(Css = ".job-search-input")]
        public  TextField JobSearchInput;

        [FindBy(XPath = "//li[@class='autocomplete-suggestion' and @data-index='0']")]
        public WebElement AutocompleteSuggestList;

        [FindBy(Css = ".job-search-button")]
        public Button JobSearchButton;

        [FindBy(XPath = "//span[contains(@class,'icon-sort intellectual')]")]
        public Link BestMatchIcon;

        [FindBy(Css = ".search-result-header")]
        public Textbox SearchResultHeader;

    }
}
