using System;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Base;
using JDI_Web.Selenium.Elements.Common;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace JDI_Web.Selenium.Elements.Complex
{
    public class Dropdown : Dropdown<IConvertible>, IDropDown
    {
        public Dropdown() { }
        public Dropdown(By selectLocator = null) : base(selectLocator, null) { }
        
        public Dropdown(By selectLocator, By optionsNamesLocator, By allOptionsNamesLocator = null)
            : base(selectLocator, optionsNamesLocator, allOptionsNamesLocator) { }
    }
    public class Dropdown<TEnum> : Selector<TEnum>, IDropDown<TEnum>
        where TEnum : IConvertible
    {
        protected GetElementType _element;
        protected GetElementType _expander;
        protected GetElementType _elementByName;

        public Dropdown() : this(null) { }
        public Dropdown(By selectLocator) : base(selectLocator, webElements: null) { }

        public Dropdown(By selectLocator, By optionsNamesLocator, By allOptionsNamesLocator = null) 
            : base(optionsNamesLocator, allOptionsNamesLocator)
        {
            SelectNameAction = (s, name) =>
            {
                var selector = new Selector(optionsNamesLocator, allOptionsNamesLocator, element: s);
                if (Element != null)
                {
                    ExpandNameAction(this, name);
                    selector.SelectNameAction(selector, name);
                }
                else
                    new SelectElement(WebElement).SelectByText(name);
            };
            SelectNumAction = (s, index) =>
            {
                var selector = new Selector(optionsNamesLocator, allOptionsNamesLocator, element: s);
                if (Element != null)
                {
                    ExpandNumAction(this, index);
                    selector.SelectNumAction(selector, index);
                }
                else
                    new SelectElement(WebElement).SelectByIndex(index);
            };
            GetValueAction = b => GetTextAction(this);
            SelectedAction = s => GetTextAction(this);
            GetOptionsAction = d =>
            {
                var selector = new Selector(optionsNamesLocator, allOptionsNamesLocator, element: d);
                var isExpanded = DisplayedNumAction(this, 1);
                if (!isExpanded) Element.Click();
                var result = selector.GetOptionsAction(selector);
                if (!isExpanded) Element.Click();
                return result;
            };
            _element = new GetElementType(selectLocator);
        }

        public void SetUp(By root, By value, By list, By expand, By elementByName)
        {
            if (root != null)
            {
                var el = new WebElement(root)
                {
                    WebAvatar = { DriverName = WebAvatar.DriverName },
                    Parent = Parent
                };
                Parent = el;
            }
            if (value != null)
            {
                _element = new GetElementType(value, this);
                if (_expander == null) _expander = _element;
            }
            if (list != null)
                _allLabels = new GetElementType(list, this);
            if (expand != null)
            {
                _expander = new GetElementType(expand, this);
                if (_element == null) _element = _expander;
            }
            if (elementByName != null)
                _elementByName = new GetElementType(elementByName, this);
        }

        protected Label Element => _element.Get(new Label(), WebAvatar);
    

        public Action<Dropdown<TEnum>, string> ExpandNameAction = (d, name) =>
        {
            //WebAvatar.context.clear();
            if (!d.Element.Displayed) return;
            d.SetWaitTimeout(0);
            if (!d.DisplayedNameAction(d, name)) d.Element.Click();
            d.RestoreWaitTimeout();
        };

        public Action<Dropdown<TEnum>, int> ExpandNumAction = (d, index) =>
        {
            //WebAvatar.context.clear();
            if (!d.DisplayedNumAction(d, index))
                d.Element.Click();
        };
        
        public new void WaitDisplayed()
        {
            Element.WaitDisplayed();
        }
        
        public new void WaitVanished()
        {
            Element.WaitVanished();
        }

        public void Wait(Func<IWebElement, bool> resultFunc)
        {
            Element.Wait(resultFunc);
        }

        public T Wait<T>(Func<IWebElement, T> resultFunc, Func<T, bool> condition)
        {
            return Element.Wait(resultFunc, condition);
        }

        public void Wait(Func<IWebElement, bool> resultFunc, int timeoutSec)
        {
            Element.Wait(resultFunc, timeoutSec);
        }

        public T Wait<T>(Func<IWebElement, T> resultFunc, Func<T, bool> condition, int timeoutSec)
        {
            return Element.Wait(resultFunc, condition, timeoutSec);
        }

        public virtual Action<Dropdown<TEnum>> ClickAction { get; set; } = d => d.Element.Click();
        public virtual Func<Dropdown<TEnum>, string> GetTextAction { get; set; } = d => d.Element.GetText;

        public void Expand()
        {
            if (!DisplayedNumAction(this, 1)) Element.Click();
        }

        public void Close()
        {
            if (DisplayedNumAction(this, 1)) Element.Click();
        }

        public void Click()
        {
            Actions.Click(d => ClickAction(this));
        }

        public string GetText => Actions.GetText(d => GetTextAction(this)); 

        public string WaitText(string text)
        {
            return Actions.WaitText(text, d => GetTextAction(this));
        }

        public string WaitMatchText(string regEx)
        {
            return Actions.WaitMatchText(regEx, d => GetTextAction(this));
        }

        public void SetAttribute(string attributeName, string value)
        {
            Element.SetAttribute(attributeName, value);
        }

        public new IWebElement WebElement => new WebElement(Locator, element:this).WebElement;

        public string GetAttribute(string name)
        {
            return Element.GetAttribute(name);
        }

        public void WaitAttribute(string name, string value)
        {
            Element.WaitAttribute(name, value);
        }


    }
}
