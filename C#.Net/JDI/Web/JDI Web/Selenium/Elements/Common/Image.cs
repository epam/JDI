using System;
using JDI_Core.Interfaces.Common;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Base;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Common
{
    public class Image : Clickable, IImage
    {
        public Image() : this (null) { }
        public Image(By byLocator = null, IWebElement webElement = null)
            : base(byLocator, webElement)
        { }

        protected Func<WebBaseElement, string> GetSourceFunc = 
            el => el.WebAvatar.FindImmediately(() => el.WebElement.GetAttribute("src"), "");
        public string GetSource()
        {
            return Invoker.DoJActionResult("Get image source for Element "+ this, GetSourceFunc);
        }

        protected Func<WebBaseElement,string> GetAltFunc =
            el => el.WebAvatar.FindImmediately(() => el.WebElement.GetAttribute("alt"), "");
        public string GetAlt()
        {
            return Invoker.DoJActionResult("Get image title for Element "+ this, GetAltFunc);
        }

    }
}
