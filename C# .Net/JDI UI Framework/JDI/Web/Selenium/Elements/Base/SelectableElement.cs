using System;
using Epam.JDI.Core.Interfaces.Base;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Elements.Base
{
    public class SelectableElement : ClickableText, ISelect
    {
        public SelectableElement()
        {
        }

        public SelectableElement(By byLocator = null, IWebElement webElement = null) : base(byLocator, webElement)
        {
        }

        protected Func<SelectableElement, bool> SelectedAction = s => s.WebElement.Selected;
        protected Action<SelectableElement> SelectAction = s => s.Click();

        public void Select()
        {
            Actions.Select(Name, (w, n) => ClickAction(this));
        }

        public bool Selected => Actions.Selected(w => SelectedAction(this));

    }
}
