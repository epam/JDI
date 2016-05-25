using JDI_Core.Attributes.Functions;
using JDI_Core.Interfaces.Complex;
using JDI_Web.Selenium.Elements.Common;

namespace JDI_Web.Selenium.Elements.Composite
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
