using System;
using Epam.JDI.Core.Interfaces.Base;
using JDI_Web.Selenium.Base;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Base
{
    public class SelectableElement : ClickableText, ISelect
    {
        public SelectableElement() : this(null) { }
        public SelectableElement(By byLocator = null, IWebElement webElement = null, WebBaseElement element = null) 
            : base(byLocator, webElement, element:element)
        {
        }

        protected Func<SelectableElement, bool> SelectedAction = s => s.WebElement.Selected;
        protected Action<SelectableElement> SelectAction = s => s.Click();

        public void Select()
        {
            Actions.Select(Name, (w, n) => ClickAction(this));
        }

        public bool Selected => Actions.Selected(w => SelectedAction(this));
        
        protected Func<WebBaseElement, string> GetValueFunc = el 
            => ((SelectableElement)el).Selected + "";

        protected Action<WebBaseElement, string> SetValueAction = (el, value) 
            => ((SelectableElement)el).Select();
        public new string Value
        {
            get => Actions.GetValue(GetValueFunc);
            set => Actions.SetValue(value, SetValueAction);
        }
    }
}
