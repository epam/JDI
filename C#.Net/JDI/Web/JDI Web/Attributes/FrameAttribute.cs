using System;
using System.Reflection;
using OpenQA.Selenium;

namespace JDI_Web.Attributes
{
    [AttributeUsage(AttributeTargets.Property | AttributeTargets.Field)]
    public class FrameAttribute : Attribute
    {
        private By _frameLocator;
        public string ById { set { _frameLocator = By.Id(value); } get { return ""; }}
        public string ByName { set { _frameLocator = By.Name(value); } get { return ""; } }
        public string ByClassName { set { _frameLocator = By.ClassName(value); } get { return ""; } }
        public string ByCssSelector { set { _frameLocator = By.CssSelector(value); } get { return ""; } }
        public string ByXPath { set { _frameLocator = By.XPath(value); } get { return ""; } }
        public string ByTagName { set { _frameLocator = By.TagName(value); } get { return ""; } }
        public string ByLinkText { set { _frameLocator = By.LinkText(value); } get { return ""; } }
        public string ByPartialLinkText { set { _frameLocator = By.PartialLinkText(value); } get { return ""; } }

        public FrameAttribute() { }
        public FrameAttribute(string frameId) { _frameLocator = By.Id(frameId); }

        public static By GetFrame(FieldInfo field)
        {
            var frame = field.GetCustomAttribute<FrameAttribute>(false);
            return frame?._frameLocator;
        }
    }
}
