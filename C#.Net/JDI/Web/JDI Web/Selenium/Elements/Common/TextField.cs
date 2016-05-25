using System;
using JDI_Core.Interfaces.Common;
using JDI_Web.Selenium.Base;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Common
{
    public class TextField : Textbox, ITextField
    {
        public TextField() : this (null) { }
        public TextField(By byLocator = null, IWebElement webElement = null)
                : base(byLocator, webElement)
        { }

        protected Func<WebBaseElement, string> GetTextFunc =
            el => el.WebAvatar.FindImmediately(() => el.WebElement.GetAttribute("value"), "");

        public void NewInput(string text)
        {
            Clear();
            Input(text);
        }

        
        protected Action<WebBaseElement, string> InputAction =
             (el,text)  => el.WebElement.SendKeys(text);

        public void Input(string text)
        {
            Actions.Input(text, InputAction);
        }

        protected Action<WebBaseElement, string> SetValueAction = (el,val) =>
            ((TextField) el).NewInput(val);

        public new string Value
        {
            get { return base.Value; }
            set { Actions.SetValue(value, SetValueAction); }
        }

        public void SendKeys(string text)
        {
            Input(text);
        }

        protected Action<WebBaseElement> ClearAction = cl => cl.WebElement.Clear(); 

        public void Clear()
        {
            Actions.Clear(ClearAction);
        }
        protected Action<WebBaseElement> FocusAction = fa => fa.WebElement.Click();
        public new void Focus()
        {
            Actions.Focus(FocusAction);
        }
    }
}
