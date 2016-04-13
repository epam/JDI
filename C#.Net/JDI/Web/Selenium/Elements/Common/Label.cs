using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Web.Selenium.Elements.Base;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Elements.Common
{
    public class Label: ClickableText, ILabel
    {
        public Label() : this (null) { }
        public Label(By byLocator = null, IWebElement webElement = null)
            : base(byLocator, webElement) { }
    }
}
