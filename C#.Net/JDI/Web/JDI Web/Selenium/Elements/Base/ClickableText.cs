using System;
using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Selenium.Base;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Base
{
    public class ClickableText : Clickable, IText
    {
        public ClickableText() : this(null) { }
        public ClickableText(By byLocator = null, IWebElement webElement = null, WebBaseElement element = null)
            : base(byLocator, webElement, element)
        {
        }

        protected Func<WebBaseElement, string> GetTextFunc =
            el =>
            {
                var getText = el.WebElement.Text ?? "";
                if (!getText.Equals(""))
                    return getText;
                var getValue = el.WebElement.GetAttribute("value");
                return getValue ?? getText;
            };

        public string Value => Actions.GetValue(GetTextFunc);

        public string GetText => Actions.GetText(GetTextFunc);

        public string WaitText(string text)
        {
            return Actions.WaitText(text, GetTextFunc);
        }

        public  string WaitMatchText(string regEx)
        {
            return Actions.WaitMatchText(regEx, GetTextFunc);
        }
    }
}
