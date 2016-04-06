using System;
using Epam.JDI.Core.Attributes.Functions;
using Epam.JDI.Core.Interfaces.Complex;
using Epam.JDI.Web.Selenium.Elements.Common;

namespace Epam.JDI.Web.Selenium.Elements.Composite
{
    public class Popup : Textbox, IPopup
    {
        public Popup()
        {
            GetTextAction = p => GetElementClass.GetTextElement().Text;
        }

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
