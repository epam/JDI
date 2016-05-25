using System;
using Epam.JDI.Core;
using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Web.Selenium.Base;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Elements.Common
{
    public class FileInput : TextField, IFileInput
    {
        public FileInput() : this (null) { }
        public FileInput(By byLocator = null, IWebElement webElement = null)
            : base(byLocator, webElement) { }
        
        protected new Action<WebBaseElement, string> SetValueAction = (el, val) => ((FileInput) el).Input(val);
    }
}
