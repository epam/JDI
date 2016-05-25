using System;
using Epam.JDI.Core;
using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Web.Selenium.Base;
using Epam.JDI.Web.Selenium.Elements.Base;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Elements.Common
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
