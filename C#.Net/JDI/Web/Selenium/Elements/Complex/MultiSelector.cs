using System;
using System.Collections.Generic;
using System.Linq;
using Epam.JDI.Commons;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Web.Selenium.DriverFactory;
using Epam.JDI.Web.Selenium.Elements.APIInteract;
using OpenQA.Selenium;
using static Epam.JDI.Core.Settings.JDISettings;

namespace Epam.JDI.Web.Selenium.Elements.Complex
{
    public abstract class MultiSelector<TEnum> : BaseSelector<TEnum>, IMultiSelector<TEnum>
        where TEnum : IConvertible
    {
        private string _separator = ", ";
        
        protected MultiSelector(By optionsNamesLocator = null, By allLabelsLocator = null)
            : base(optionsNamesLocator, allLabelsLocator)
        {
            SelectedNameAction = (m, name) => SelectedElementAction(this, GetWebElement(name));
            SelectedNumAction = (m, index) => SelectedElementAction(this, GetWebElement(index));
            GetValueAction = m => AreSelected().Print();
            SetValueAction = (m, value) => SelectListNamesAction(this, value.Split(_separator)); ;
        }

        public Action<MultiSelector<TEnum>> ClearAction = m =>
        {
            if (!m.HasLocator && m.AllLabels == null)
                throw Exception("Can't clear options. No optionsNamesLocator and allLabelsLocator found");
            if (m.Locator.ToString().Contains("{0}"))
                throw Exception(
                    "Can't clear options. Specify allLabelsLocator or fix optionsNamesLocator (should not contain '{0}')");
            if (m.AllLabels != null)
            {
                m.ClearElements(m.AllLabels.WebElements);
                return;
            }
            var elements = m.WebAvatar.SearchAll().WebElements;
            if (elements.Count == 1 && elements[0].TagName.Equals("select"))
                if (m.Selector.Options.Any())
                {
                    m.Selector.DeselectAll();
                    return;
                }
                else
                    throw Exception($"<select> tag has no <option> tags. Please Clarify element locator ({m})");
            if (elements.Count == 1 && elements[0].TagName.Equals("ul"))
                elements = elements[0].FindElements(By.TagName("li")).ToList();
            m.ClearElements(elements);
        };

        private void ClearElements(IList<IWebElement> els)
        {
            els.Where(el => SelectedNameAction(this, el.Text)).ForEach(el => el.Click());
        }

        protected IWebElement GetWebElement(string name)
        {
            if (!HasLocator && AllLabels == null)
                throw Exception("Can't get option. No optionsNamesLocator and allLabelsLocator found");
            if (Locator.ToString().Contains("{0}"))
                return new GetElementModule {
                    ByLocator = Locator.FillByTemplate(name),
                    Element = this }
                .WebElements[0];
            if (AllLabels != null)
                return GetWebElement(AllLabels.WebElements, name);
            return GetWebElement(GetElementsFromTag(), name);
        }

        private IWebElement GetWebElement(IList<IWebElement> els, string name)
        {
            if (els == null)
                throw Exception("Can't get option. No optionsNamesLocator and allLabelsLocator found");
            var elements = els.Where(el => el.Text.Equals(name)).ToList();
            if (elements.Count == 1)
                return elements[0];
            throw Exception("Can't get option. No optionsNamesLocator and allLabelsLocator found");
        }

        protected IWebElement GetWebElement(int index)
        {
            if (!HasLocator && AllLabels == null)
                throw Exception("Can't get option. No optionsNamesLocator and allLabelsLocator found");
            if (Locator.ToString().Contains("{0}"))
                throw Exception("Can't get options. Specify allLabelsLocator or fix optionsNamesLocator (should not contain '{0}')");
            if (AllLabels != null)
                return GetWebElement(AllLabels.WebElements, index);
            return GetWebElement(GetElementsFromTag(), index);
        }

        private IWebElement GetWebElement(IList<IWebElement> els, int index)
        {
            if (index <= 0)
                throw Exception($"Can't get option with index '{index}'. Index should be 1 or more");
            if (index > els.Count)
                throw Exception($"Can't get option with index '{index}'. Found only {els.Count} options");
            return els[index - 1];
        }


        protected Action<MultiSelector<TEnum>, IList<string>> SelectListNamesAction = 
            (m, names) => names.ForEach(name => m.SelectNameAction(m, name));
        protected Action<MultiSelector<TEnum>, IList<int>> SelectListIndexesAction =
            (m, indexes) => indexes.ForEach(index => m.SelectNumAction(m, index));
        
        
        public IMultiSelector<TEnum> SetValuesSeparator(string separator)
        {
            _separator = separator;
            return this;
        }

        public void Select(params string[] names)
        {
            Actions.Select((m, v) => SelectListNamesAction(this, names), names);
        }

        public void Select(params TEnum[] names)
        {
            Select(names.Select(name => name.ToString()).ToArray());
        }

        public void Select(params int[] indexes)
        {
            Actions.Select((m, i) => SelectListIndexesAction(this, indexes), indexes);
        }

        public void Check(params string[] names)
        {
            Clear();
            Select(names);
        }

        public void Check(params TEnum[] names)
        {
            Clear();
            Select(names);
        }

        public void Check(params int[] indexes)
        {
            Clear();
            Select(indexes);
        }

        public void Uncheck(params string[] names)
        {
            CheckAll();
            Select(names);
        }

        public void Uncheck(params TEnum[] names)
        {
            CheckAll();
            Select(names);
        }

        public void Uncheck(params int[] indexes)
        {
            CheckAll();
            Select(indexes);
        }

        public IList<string> AreSelected()
        {
            return Actions.AreSelected(m => Names, (m, name) => SelectedNameAction(this, name));
        }

        public void WaitSelected(params TEnum[] names)
        {
            WaitSelected(names.Select(name => name.ToString()).ToArray());
        }

        public void WaitSelected(params string[] names)
        {
            Actions.WaitSelected((m, n) => Timer.Wait(() => SelectedNameAction(this, n)), names);
        }

        public IList<string> AreDeselected()
        {
            return Actions.AreDeselected(m => Names, n => Timer.Wait(() => SelectedNameAction(this, n)));
        }

        public void WaitDeselected(params TEnum[] names)
        {
            WaitDeselected(names.Select(name => name.ToString()).ToArray());
        }

        public void WaitDeselected(params string[] names)
        {
            Actions.WaitDeselected((m, n) => Timer.Wait(() =>SelectedNameAction(this, n)), names);
        }

        public void Clear()
        {
            Invoker.DoJAction("Clear Options", m => ClearAction(this));
        }

        public void CheckAll()
        {
            Options.Where(label => !SelectedNameAction(this, label)).ForEach(label => SelectNameAction(this, label));
        }
        
        public void SelectAll() 
        {
            CheckAll();
        }
        public void UncheckAll()
        {
            Clear();
        }

    }
}
