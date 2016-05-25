using JDI_Core.Interfaces.Common;
using JDI_Web.Selenium.Elements.Base;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Common
{
    public class Label: ClickableText, ILabel
    {
        public Label() : this (null) { }
        public Label(By byLocator = null, IWebElement webElement = null)
            : base(byLocator, webElement) { }
    }
}
