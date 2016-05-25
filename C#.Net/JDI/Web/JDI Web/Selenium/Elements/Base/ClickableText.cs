using System;
using JDI_Core.Interfaces.Common;
using JDI_Web.Selenium.Base;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Base
{
    public class ClickableText : Clickable, IText
    {
        public ClickableText() : this (null) { }
        public ClickableText(By byLocator = null, IWebElement webElement = null)
            : base(byLocator, webElement)
        {
        }

        protected Func<WebBaseElement, string> GetTextFunc =
            el => el.WebAvatar.FindImmediately(() => el.WebElement.Text, "");

        public string Value => Actions.GetValue(GetTextFunc);


        public string Text => Actions.GetText(GetTextFunc);

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
