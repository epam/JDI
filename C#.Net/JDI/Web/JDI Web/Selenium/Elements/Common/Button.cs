using System;
using JDI_Core.Interfaces.Common;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Base;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Common
{
    public class Button : ClickableText, IButton
    {
        public Button() : this (null) { }
        public Button(By byLocator = null, IWebElement webElement = null)
            : base(byLocator, webElement) { }

        protected new Func<WebBaseElement, string> GetTextFunc =
           el => el.WebAvatar.FindImmediately(() => el.WebElement.GetAttribute("value"), "");
    }
}
