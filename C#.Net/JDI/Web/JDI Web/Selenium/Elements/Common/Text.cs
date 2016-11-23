using System;
using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Base;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Common
{
    public class Text : WebElement, IText
    {
        public Text() : this(null) { }
        public Text(By byLocator = null, IWebElement webElement = null, WebBaseElement element = null)
            : base(byLocator, webElement, element:element) { }

        protected virtual Func<WebBaseElement, string> GetTextAction { get; set; } = el =>
        {
            var getText = el.WebElement.Text ?? "";
            if (!getText.Equals(""))
                return getText;
            var getValue = el.WebElement.GetAttribute("value");
            return getValue ?? getText;
        };

        public string GetText => Actions.GetText(GetTextAction);
        
        protected Func<WebBaseElement, string> GetValueFunc = el => ((Text)el).GetTextAction(el);
        
        public string Value => Actions.GetValue(GetValueFunc);

        public string WaitText(string text)
        {
            return Actions.WaitText(text, GetTextAction);
        }

        public string WaitMatchText(string regEx)
        {
            return Actions.WaitMatchText(regEx, GetTextAction);
        }
    }
}