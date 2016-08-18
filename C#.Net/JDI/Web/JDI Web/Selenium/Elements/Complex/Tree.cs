using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using JDI_Commons;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.DriverFactory;
using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;
using static Epam.JDI.Core.Settings.JDISettings;

namespace JDI_Web.Selenium.Elements.Complex
{
    public class Tree : Selector
    {
        public string Separator = "\\|";
        public Tree UseSeparator(string separator)
        {
            Separator = separator;
            return this;
        }

        public Tree()
        {
            SelectNameAction = (m, name) => ChooseItemAction(this, new [] {name});
        }

        public Tree(By optionsNamesLocatorTemplate, List<IWebElement> webElements = null) 
            : base(optionsNamesLocatorTemplate, webElements) { }

        public Tree(By optionsNamesLocatorTemplate, By allOptionsNamesLocator) 
            : base(optionsNamesLocatorTemplate, allOptionsNamesLocator) { }
        
        protected Action<WebBaseElement, IWebElement> HoverAction = (m, el) =>
        {
            var action = new Actions(m.WebDriver);
            action.MoveToElement(el).ClickAndHold().Build().Perform();
        };
        

        private IList<string> SplitToList(string[] str, string separator)
        {
            return (str.Length == 1
                ? Regex.Split(str[0], separator)
                : str).ToList();
        }
        
        public void Select(params string[] names)
        {
            Actions.Select(names.Print(), (w, n) => ChooseItemAction(this, names));
        }

        protected Action<Tree, string[]> ChooseItemAction =
            (m, names) =>
            {
                var nodes = m.SplitToList(names, m.Separator);
                ISearchContext ctx = m.WebDriver;
                nodes.ForEach(node =>
                {
                    var elements = ctx.FindElements(m.Locator.FillByTemplate(node));
                    if (elements == null || elements.Count != 0)
                        throw Exception($"Can't select element by path '{names.Print(m.Separator)}'");
                    var element = elements.First();
                    element.Click();
                    ctx = element;
                });
            };
        
    }
}
