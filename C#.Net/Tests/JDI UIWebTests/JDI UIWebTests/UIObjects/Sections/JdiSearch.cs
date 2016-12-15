using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_UIWebTests.UIObjects.Sections
{
    public sealed class JdiSearch:Search
    {        
        [FindBy(Css = ".search-field input")]
        public ITextField SearchInput;

        [FindBy(Css = ".search>.icon-search")]
        public IButton SearchButton;

        [FindBy(Css = ".icon-search.active")]
        public IButton SearchButonActive;
    }
}
