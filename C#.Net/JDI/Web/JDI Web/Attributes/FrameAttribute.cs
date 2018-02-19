﻿using System;
using System.Reflection;
using OpenQA.Selenium;

namespace JDI_Web.Attributes
{
    [AttributeUsage(AttributeTargets.Property | AttributeTargets.Field)]
    public class FrameAttribute : Attribute
    {
        private By _frameLocator;

        public string Id
        {
            set { _frameLocator = By.Id(value); }
            get { return ""; }
        }

        public string Name
        {
            set { _frameLocator = By.Name(value); }
            get { return ""; }
        }

        public string Class
        {
            set { _frameLocator = By.ClassName(value); }
            get { return ""; }
        }

        public string Css
        {
            set { _frameLocator = By.CssSelector(value); }
            get { return ""; }
        }

        public string XPath
        {
            set { _frameLocator = By.XPath(value); }
            get { return ""; }
        }

        public string Tag
        {
            set { _frameLocator = By.TagName(value); }
            get { return ""; }
        }

        public string LinkText
        {
            set { _frameLocator = By.LinkText(value); }
            get { return ""; }
        }

        public string PartialLinkText
        {
            set { _frameLocator = By.PartialLinkText(value); }
            get { return ""; }
        }


        public FrameAttribute() { }
        public FrameAttribute(string frameId) { _frameLocator = By.Id(frameId); }

        public static By GetFrame(FieldInfo field)
        {
            var frame = field.GetCustomAttribute<FrameAttribute>(false);
            return frame?._frameLocator;
        }
    }
}
