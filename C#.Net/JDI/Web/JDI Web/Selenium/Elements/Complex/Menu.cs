using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_Commons;
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
        public string Separator = "\\.";
        public Menu<TEnum> UseSeparator(string separator)
        {
            Separator = separator;
            return this;
        }

        public Menu() { }

        public Menu(By optionsNamesLocatorTemplate, List<IWebElement> webElements = null) 
            : base(optionsNamesLocatorTemplate, webElements) { }

        public Menu(By optionsNamesLocatorTemplate, By allOptionsNamesLocator) 
            : base(optionsNamesLocatorTemplate, allOptionsNamesLocator) {
            MenuLevelsLocators.Add(optionsNamesLocatorTemplate);
        }
        public Menu(List<By> menuLevelsLocators)
        {
            MenuLevelsLocators = menuLevelsLocators;
        }

        protected Action<Menu<TEnum>, string> HoverAction = (m, name) =>
        {
            m.ChooseItemAction(m, name, el =>
            {
                var action = new Actions(m.WebDriver);
                action.MoveToElement(el).ClickAndHold().Build().Perform();
            });
        };

        public void Hover(string name)
        {
            Actions.Hover(name, (m, n) => HoverAction(this, name));
        }

        protected Action<Menu<TEnum>, string> HoverAndClickAction = (m, name) =>
        {
            var split = Regex.Split(name, m.Separator).ToList();
            if (split.Count > m.MenuLevelsLocators.Count)
                throw Exception(
                    $"Can't hover and click on element ({m}) by value: {name}. Amount of locators ({m.MenuLevelsLocators.Count}) less than select path length ({split.Count})");
            if (split.Count > 1)
                m.Hover(split.ListCopy(to:-1).Print(","));
            var lastIndex = split.Count - 1;
            var selector = new Selector(m.MenuLevelsLocators[lastIndex]) {Parent = m.Parent};
            selector.Select(split[lastIndex]);
        };
        public void Hover(TEnum name)
        {
            Hover(name.ToString());
        }

        public void HoverAndClick(string name)
        {
            Actions.Select(name, (m, n) => HoverAndClickAction(this, name));
        }
        public void HoverAndClick(TEnum name)
        {
            HoverAndClick(name.ToString());
        }
        public void HoverAndSelect(string name)
        {
            HoverAndClick(name);
        }
        public void HoverAndSelect(TEnum name)
        {
            HoverAndSelect(name.ToString());
        }
        
        protected Action<Menu<TEnum>, string> SelectAction = 
            (m, name) => m.ChooseItemAction(m, name, el => el.Click());

        protected Action<Menu<TEnum>, string, Action<IWebElement>> ChooseItemAction =
            (m, name, action) =>
            {
                var nodes = Regex.Split(name, m.Separator).ToList();
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
