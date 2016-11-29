using System;
using Epam.JDI.Core.Attributes.Functions;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Common;

namespace JDI_Web.Selenium.Elements.Composite
{
    public class Popup : Text, IPopup
    {
        public Popup() { }

        protected override Func<WebBaseElement, string> GetTextAction => p => GetElementClass.GetTextElement().GetText;

        public void Ok()
        {
            GetElementClass.GetButton(Functions.Ok).Click();
        }

        public void Cancel()
        {
            GetElementClass.GetButton(Functions.Cancel).Click();
        }

        public void Close()
        {
            GetElementClass.GetButton(Functions.Close).Click();
        }
    }
}
