using System;
using System.Collections.Generic;
using System.Linq;
using Epam.JDI.Core.Interfaces.Complex;
using OpenQA.Selenium;
using static Epam.JDI.Core.Settings.JDISettings;

namespace Epam.JDI.Web.Selenium.Elements.Complex
{
    public class Selector : Selector<IConvertible>, ISelector
    {
        public Selector() { }
        public Selector(By optionsNamesLocatorTemplate, By allOptionsNamesLocator) 
            : base(optionsNamesLocatorTemplate, allOptionsNamesLocator) { }
    }
    public class Selector<TEnum> : BaseSelector<TEnum>, ISelector<TEnum>
        where TEnum : IConvertible
    {
        protected Selector() : this(null, null) { }
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

        public void Select(int index) {
            Actions.Select(index, (s, n) => SelectNumAction(this, index));
        }

        public string Selected() {
            return Actions.Selected(s => SelectedAction(this));
        }

        public int SelectedIndex() {
            return Actions.SelectedIndex(s => SelectedIndexAction(this));
        }

        public Func<Selector<TEnum>, string> SelectedAction = s =>
            {
                if (s.AllLabels != null)
                    return s.Selected(s.AllLabels.WebElements);
                if (s.Locator.ToString().Contains("{0}"))
                    throw Exception(
                        "Can't get Selected options. Override getSelectedAction or place locator to <select> tag");
                var els = s.WebAvatar.SearchAll().WebElements;
                return s.Selected(els.Count == 1 ? s.Selector.Options.ToList() : els);
            };

        public string Selected(IList<IWebElement> els) {
            var element = els.FirstOrDefault(el => SelectedElementAction(this, el));
            if (element == null)
                throw Exception("No elements selected. Override getSelectedAction or place locator to <select> tag");
            return element.Text;
        }

        public Func<Selector<TEnum>, int> SelectedIndexAction = s =>
        {
            if (s.AllLabels != null)
                return s.SelectedIndex(s.AllLabels.WebElements);
            if (s.Locator.ToString().Contains("{0}"))
                throw Exception(
                    "Can't get Selected options. Override getSelectedAction or place locator to <select> tag");
            var els = s.WebAvatar.SearchAll().WebElements;
            return s.SelectedIndex(els.Count == 1 ? s.Selector.Options.ToList() : els);
        };

        private int SelectedIndex(List<IWebElement> els) {
            var index = els.FindIndex(el => SelectedElementAction(this, el)) + 1;
            if (index == 0)
                throw Exception("No elements selected. Override getSelectedAction or place locator to <select> tag");
            return index;
        }

    }
}
