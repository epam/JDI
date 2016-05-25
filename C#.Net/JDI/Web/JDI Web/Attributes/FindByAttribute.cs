using System;
using System.Reflection;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace JDI_Web.Attributes
{
    [AttributeUsage(AttributeTargets.Property | AttributeTargets.Field)]
    public class FindByAttribute : Attribute
    {
        private By _locator;
        public string Id { set { _locator = By.Id(value); } get { return ""; } }
        public string Name { set { _locator = By.Name(value); } get { return ""; } }
        public string ClassName { set { _locator = By.ClassName(value); } get { return ""; } }
        public string Css { set { _locator = By.CssSelector(value); } get { return ""; } }
        public string XPath { set { _locator = By.XPath(value); } get { return ""; } }
        public string Tag { set { _locator = By.TagName(value); } get { return ""; } }
        public string LinkText { set { _locator = By.LinkText(value); } get { return ""; } }
        public string PartialLinkText { set { _locator = By.PartialLinkText(value); } get { return ""; } }

        public static By FindsByLocator(FindsByAttribute fbAttr)
        {
            switch (fbAttr.How)
            {
                case How.Id:
                    return By.Id(fbAttr.Using);
                case How.Name:
                    return By.Name(fbAttr.Using);
                case How.ClassName:
                    return By.ClassName(fbAttr.Using);
                case How.CssSelector:
                    return By.CssSelector(fbAttr.Using);
                case How.XPath:
                    return By.XPath(fbAttr.Using);
                case How.TagName:
                    return By.TagName(fbAttr.Using);
                case How.LinkText:
                    return By.LinkText(fbAttr.Using);
                case How.PartialLinkText:
                    return By.PartialLinkText(fbAttr.Using);
                default:
                    return By.Id("Undefined locator");
            }
        }

        public static By Locator(FieldInfo field)
        {
            var locator = field.GetCustomAttribute<FindByAttribute>(false);
            return locator?._locator;
        }

        public static By FindsByLocator(FieldInfo field)
        {
            var locator = field.GetCustomAttribute<FindsByAttribute>(false);
            return locator != null ? FindsByLocator(locator) : null;
        }

        public static By Locator(object obj)
        {
            var locator = obj.GetType().GetCustomAttribute<FindByAttribute>(false);
            return locator?._locator;
        }
    }
}

