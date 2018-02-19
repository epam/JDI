using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Selenium.Elements.Composite;
using OpenQA.Selenium.Support.PageObjects;

namespace JDI_UIWebTests.UIObjects.Sections
{
    public sealed class JdiSearch : Search
    {
        [FindsBy(How = How.CssSelector, Using = ".search-field input")]
        public ITextField SearchInput;

        [FindsBy(How = How.CssSelector, Using = ".search>.icon-search")]
        public new IButton SearchButton;

        [FindsBy(How = How.CssSelector, Using = ".icon-search.active")]
        public IButton SearchButonActive;
    }
}
