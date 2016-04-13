using Epam.JDI.Core.Interfaces.Complex;
using System;
using Epam.JDI.Commons;
using Epam.JDI.Web.Selenium.Base;
using Epam.JDI.Web.Selenium.Elements.Base;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Elements.Complex
{
    public class DropList : DropList<IConvertible>, IDropList
    {
        public DropList() { }

        public DropList(By valueLocator) : base(valueLocator) { }

        public DropList(By valueLocator, By optionsNamesLocator, By allOptionsNamesLocator = null)
            : base(valueLocator, optionsNamesLocator, allOptionsNamesLocator) { }
    }

    public class DropList<TEnum> : MultiSelector<TEnum>, IDropList<TEnum>
        where TEnum : IConvertible
    {
        private readonly GetElementType _button = new GetElementType();

        public DropList() : this(null) { }

        public DropList(By valueLocator) : base(valueLocator)
        {
            InitActions();
        }
        
        public DropList(By valueLocator, By optionsNamesLocator, By allOptionsNamesLocator = null)
            : base(optionsNamesLocator, allOptionsNamesLocator)
        {
            InitActions();
            _button = new GetElementType(valueLocator);
        }

        private void InitActions()
        {
            SelectListNamesAction = (d, names) =>
            {
                if (names == null || names.Count == 0)
                    return;
                if (Button != null)
                {
                    ExpandNameAction(this, names[0]);
                    SelectListNamesAction(this, names);
                }
                else
                    names.ForEach(name => Selector.SelectByText(name));
            };
            SelectListIndexesAction = (d, indexes) =>
            {
                if (indexes == null || indexes.Count == 0)
                    return;
                if (Button != null)
                {
                    ExpandNumAction(this, indexes[0]);
                    SelectListIndexesAction(this, indexes);
                }
                else
                    indexes.ForEach(index => Selector.SelectByIndex(index));
            };
            ClearAction = d =>
            {
                if (Button != null)
                    ExpandNumAction(this, 1);
                ClearAction(this);
            };
            GetValueAction = d => GetTextAction(this);
        }

        protected Clickable Button => _button.Get(new Clickable(), WebAvatar);

        protected Action<DropList<TEnum>, string> ExpandNameAction = (d, name) =>
        {
            if (!d.DisplayedNameAction(d, name))
                d.Button.Click();
        };

        protected Action<DropList<TEnum>, int> ExpandNumAction = (d, index) =>
        {
            if (!d.DisplayedNumAction(d, index))
                d.Button.Click();
        };
        
        protected Func<DropList<TEnum>, string> GetTextAction = 
            d => d.WebElement.GetAttribute("value");
        
        
        public new void WaitDisplayed()
        {
            Button.WaitDisplayed();
        }
        
        public new void WaitVanished()
        {
            Button.WaitVanished();
        }

        public void Wait(Func<IWebElement, bool> resultFunc)
        {
            Button.Wait(resultFunc);
        }

        public T Wait<T>(Func<IWebElement, T> resultFunc, Func<T, bool> condition)
        {
            return Button.Wait(resultFunc, condition);
        }

        public void Wait(Func<IWebElement, bool> resultFunc, int timeoutSec)
        {
            Button.Wait(resultFunc, timeoutSec);
        }

        public T Wait<T>(Func<IWebElement, T> resultFunc, Func<T, bool> condition, int timeoutSec)
        {
            return Button.Wait(resultFunc, condition, timeoutSec);
        }

        public void SetAttribute(string attributeName, string value)
        {
            Button.SetAttribute(attributeName, value);
        }

        public string Text => Actions.GetText(d => GetTextAction(this));

        public string WaitText(string text)
        {
            return Actions.WaitText(text, d => GetTextAction(this));
        }

        public string WaitMatchText(string regEx)
        {
            return Actions.WaitMatchText(regEx, d => GetTextAction(this));
        }

        public new IWebElement WebElement => new WebElement(Locator).WebElement;

        public string GetAttribute(string name)
        {
            return Button.GetAttribute(name);
        }

        public void WaitAttribute(string name, string value)
        {
            Button.WaitAttribute(name, value);
        }
    }
}
