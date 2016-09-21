using System;
using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Selenium.Base;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Common
{
    public class FileInput : TextField, IFileInput
    {
        public FileInput() : this(null) { }
        public FileInput(By byLocator = null, IWebElement webElement = null, WebBaseElement element = null)
            : base(byLocator, webElement, element:element) { }

        protected new Action<WebBaseElement, string> SetValueAction = (el, val) => ((FileInput) el).Input(val);
    }
}
