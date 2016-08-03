﻿using System;
using System.Collections.Generic;
using System.Linq;
using Epam.JDI.Core.Interfaces.Complex;
using OpenQA.Selenium;
using static Epam.JDI.Core.Settings.JDISettings;

namespace JDI_Web.Selenium.Elements.Complex
{
    public class Selector : Selector<IConvertible>, ISelector
    {
        public Selector() { }
        public Selector(By optionsNamesLocatorTemplate) : base(optionsNamesLocatorTemplate) { }
        public Selector(By optionsNamesLocatorTemplate, By allOptionsNamesLocator) 
            : base(optionsNamesLocatorTemplate, allOptionsNamesLocator) { }
    }
    public class Selector<TEnum> : BaseSelector<TEnum>, ISelector<TEnum>
        where TEnum : IConvertible
    {
        protected Selector() : this(null, webElements: null) { }

        public Selector(By optionsNamesLocatorTemplate, List<IWebElement> webElements = null) : 
            base(optionsNamesLocatorTemplate, webElements) { }
        public Selector(By optionsNamesLocatorTemplate, By allOptionsNamesLocator)
            : base(optionsNamesLocatorTemplate, allOptionsNamesLocator)
        {
            SelectedNameAction = (s, name) => ((Selector<TEnum>) s).SelectedAction(this).Equals(name);
            SelectedNumAction = (s, num) => ((Selector<TEnum>)s).SelectedIndexAction(this) == num;
            GetValueAction = s => ((Selector<TEnum>)s).Selected();
        }
        public void Select(string name) {
            Actions.Select(name, (s, n) => SelectNameAction(this, name));
        }

        public void Select(TEnum enumType) {
            Select(enumType.ToString());
        }

        public void Select(int num) {
            Actions.Select(num, (s, n) => SelectNumAction(this, num));
        }

        public string Selected() {
            return Actions.Selected(s => SelectedAction(this));
        }

        public int SelectedIndex() {
            return Actions.SelectedIndex(s => SelectedIndexAction(this));
        }

        public Func<Selector<TEnum>, string> SelectedAction = s => s.Selected(s.Elements);

        public string Selected(IList<IWebElement> els) {
            var element = els.FirstOrDefault(el => SelectedElementAction(this, el));
            if (element == null)
                throw Exception("No elements selected. Override getSelectedAction or place locator to <select> tag");
            return element.Text;
        }

        public Func<Selector<TEnum>, int> SelectedIndexAction = s => s.SelectedIndex(s.Elements);

        private int SelectedIndex(IList<IWebElement> els) {
            var num = els.ToList().FindIndex(el => SelectedElementAction(this, el)) + 1;
            if (num == 0)
                throw Exception("No elements selected. Override getSelectedAction or place locator to <select> tag");
            return num;
        }

    }
}
