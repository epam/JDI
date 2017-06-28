using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
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

        protected string GetTextAction()
        {
            return alert.Text;
        }
    }
}
