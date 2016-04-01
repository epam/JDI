using System;
using Epam.JDI.Core;
using Epam.JDI.Core.Interfaces.Base;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Elements.Base
{
    public class OptionElement : ClickableText, ISelect
    {
        public OptionElement() : this (null) { }
        public OptionElement(By byLocator = null, IWebElement webElement = null)
            : base(byLocator, webElement) { }

        public void Select()
        {
            Click();
        }
        
        protected Func<WebBaseElement, bool> IsSelectedAction =
             el => el.WebAvatar.FindImmediately(() => el.WebElement.Selected, false);

        public bool Selected => Actions.Selected(IsSelectedAction);
    }
}
