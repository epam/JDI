using System;
using JDI_Core.Interfaces.Common;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Base;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Common
{
    public class Textbox : WebElement, IText
    {
        public Textbox() : this (null) { }
        public Textbox(By byLocator = null, IWebElement webElement = null)
            : base(byLocator, webElement) { }

        protected Func<WebBaseElement, string> GetTextAction =
            el => el.WebAvatar.FindImmediately(() => el.WebElement.Text, "");

        public string Text => Actions.GetText(GetTextAction);
        
        protected Func<WebBaseElement, string> GetValueFunc = el => ((Textbox)el).GetTextAction(el);
        
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