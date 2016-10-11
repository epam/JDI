using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using Epam.JDI.Core.Settings;
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
            SelectNameAction = (s, name) =>
            {
                ExpandNameAction(this, name);
                var nodes = Regex.Split(name, " \\> ");
                ISearchContext ctx = WebDriver;
                if (_treeLocators.Count < nodes.Length) return;
                for (var i = 0; i < nodes.Length; i++)
                {
                    var value = nodes[i];
                    var els = ctx.FindElements(_treeLocators[i].CorrectXPath());
                    if (!els.Any())
                        throw JDISettings.Exception("No elements found for locator: " + _treeLocators[i] + "in TreeDropdown " + this);
                    ctx = els.FirstOrDefault(el => el.Text.Equals(value));
                    if (ctx == null)
                        throw JDISettings.Exception("Can't find: " + value + "in TreeDropdown " + this);
                    if (i < nodes.Length - 1) {
                        var nextValue =
                            ctx.FindElements(_treeLocators[i + 1].CorrectXPath()).Any(el => el.Text.Equals(nodes[i + 1]));
                        if (nextValue) continue;
                    }
                    ((IWebElement) ctx).Click();
                }
            };
            ExpandNameAction = (d, name) =>
            {
                if (WebDriver.FindElements(_treeLocators[0]).Count == 0)
                    Element.Click();
            };
        }
    }
}
