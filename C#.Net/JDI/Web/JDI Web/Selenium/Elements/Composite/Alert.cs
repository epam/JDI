using JDI_Web.Selenium.Base;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Composite
{
    public class Alert : Popup
    {
        private IAlert alert;
        private IAlert GetAlert()
        {
            if (alert == null)
                alert = new WebBaseElement().WebDriver.SwitchTo().Alert();
            return alert;
        }

        protected void OkAction()
        {
            alert.Accept();
        }

        protected void cancelAction()
        {
            alert.Dismiss();
        }

        protected void closeAction()
        {
            alert.Dismiss();
        }

        protected new string GetTextAction()
        {
            return alert.Text;
        }
    }
}
