using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_Commons;
using JDI_Web.Selenium.Base;
using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;
using static Epam.JDI.Core.Settings.JDISettings;

namespace JDI_Web.Selenium.Elements.Complex
{
    public class Menu : Menu<IConvertible>, IMenu { }
    public class Menu<TEnum> : Selector<TEnum>, IMenu<TEnum>
        where TEnum : IConvertible
    {
        public List<By> MenuLevelsLocators = new List<By>();
        public string Separator = "\\|";
        public Menu<TEnum> UseSeparator(string separator)
        {
            Separator = separator;
            return this;
        }

        public Menu()
        {
            SelectNameAction = (m, name) => ChooseItemAction(this, new [] {name}, el => el.Click());
        }

        public Menu(By optionsNamesLocatorTemplate, List<IWebElement> webElements = null) 
            : base(optionsNamesLocatorTemplate, webElements)
        {
            MenuLevelsLocators.Add(optionsNamesLocatorTemplate);
        }

        public Menu(By optionsNamesLocatorTemplate, By allOptionsNamesLocator) 
            : base(optionsNamesLocatorTemplate, allOptionsNamesLocator) {
            MenuLevelsLocators.Add(optionsNamesLocatorTemplate);
        }
        public Menu(List<By> menuLevelsLocators)
        {
            MenuLevelsLocators = menuLevelsLocators;
        }

        protected Action<WebBaseElement, string[]> HoverAction = (w, names) =>
        {
            var m = (Menu<TEnum>) w;
            m.ChooseItemAction(m, names, el =>
            {
                var action = new Actions(m.WebDriver);
                action.MoveToElement(el).ClickAndHold().Build().Perform();
            });
        };

        public void Hover(params string[] names)
        {
            if (names == null || names.Length == 0)
                return;
            Actions.Hover(names.Print(Separator), (w, n) =>
            {
                var split = SplitToList(names, Separator);
                foreach (var name in split)
                    HoverAction(this, new []{name});
            });
        }

        private IList<string> SplitToList(string[] str, string separator)
        {
            return (str.Length == 1
                ? Regex.Split(str[0], separator)
                : str).ToList();
        }

        private void Select(By locator, string name)
        {
            new Selector(locator) {Parent = Parent}.Select(name);
        }

        protected Action<Menu<TEnum>, string[]> HoverAndClickAction = (m, names) =>
        {
            if (names == null || names.Length == 0)
                return;
            if (names.Length == 1)
                m.Select(m.Locator, names[0]);
            var split = m.SplitToList(names, m.Separator);
            if (split.Count > m.MenuLevelsLocators.Count)
                throw Exception($"Can't hover and click on element ({m}) by value: {names.Print(m.Separator)}. Amount of locators ({m.MenuLevelsLocators.Count}) less than select path length ({split.Count})");
            if (split.Count > 1)
                m.Hover(split.ListCopy(to:-1).Print(m.Separator));
            var lastIndex = split.Count - 1;
            m.Select(m.MenuLevelsLocators[lastIndex], split[lastIndex]);
        };
        public void Hover(TEnum name)
        {
            Hover(name.ToString());
        }

        public void Select(params string[] names)
        {
            HoverAndClick(names);
        }
        public void HoverAndClick(params string[] names)
        {
            Actions.Select(names.Print(Separator), (m, n) => HoverAndClickAction(this, names));
        }
        public void HoverAndClick(TEnum name)
        {
            HoverAndClick(name.ToString());
        }
        public void HoverAndSelect(params string[] name)
        {
            HoverAndClick(name);
        }
        public void HoverAndSelect(TEnum name)
        {
            HoverAndSelect(name.ToString());
        }

        protected Action<Menu<TEnum>, string[], Action<IWebElement>> ChooseItemAction =
            (m, names, action) =>
            {
                var nodes = m.SplitToList(names, m.Separator);
                if (m.MenuLevelsLocators.Count == 0 && m.HasLocator)
                    m.MenuLevelsLocators.Add(m.Locator);
                if (m.MenuLevelsLocators.Count < nodes.Count) return;
                for (var i = 0; i < nodes.Count; i++)
                {
                    var value = nodes[i];
                    var elements = new Selector(m.MenuLevelsLocators[i]).Elements;
                    var element = elements.FirstOrDefault(el => el.Text.Equals(value));
                    action(element);
                }
            };

        public void SetUp(List<By> menuLevelsLocators)
        {
            MenuLevelsLocators = menuLevelsLocators;
        }
    }
}
