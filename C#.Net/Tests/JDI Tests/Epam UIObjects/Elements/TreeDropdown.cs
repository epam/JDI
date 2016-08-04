using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Elements.Complex;
using OpenQA.Selenium;

namespace JDI_Tests.Epam_UIObjects.Elements
{
    public class TreeDropdown : TreeDropdown<Enum>
    {
        public TreeDropdown() { }

        public TreeDropdown(By selectLocator) : base(selectLocator) { }

        public TreeDropdown(By selectLocator, List<By> treeLocators)
            : base(selectLocator, treeLocators) { }
    }

    public class TreeDropdown<TEnum> : Dropdown<TEnum>
        where TEnum : IConvertible
    {
        private readonly List<By> _treeLocators;

        public TreeDropdown() { }

        public TreeDropdown(By selectLocator) : base(selectLocator) { }

        public TreeDropdown(By selectLocator, List<By> treeLocators)
            : base(selectLocator, selectLocator, null)
        {
            _treeLocators = treeLocators;
        }

        protected void ExpandAction()
        {
            if (WebDriver.FindElements(_treeLocators[0]).Count == 0)
                Element.Click();
        }

        protected void SelectAction(string name)
        {
            ExpandAction();
            var nodes = Regex.Split(name, " \\> ");
            ISearchContext ctx = WebDriver;
            if (_treeLocators.Count < nodes.Length) return;
            for (var i = 0; i < nodes.Length; i++)
            {
                var value = nodes[i];
                ctx = ctx.FindElements(_treeLocators[i].CorrectXPath())
                    .First(el => el.Text.Equals(value));
                ((IWebElement) ctx).Click();
            }
        }
    }
}
