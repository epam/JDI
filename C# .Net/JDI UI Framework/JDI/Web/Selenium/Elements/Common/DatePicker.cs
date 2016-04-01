using Epam.JDI.Core.Interfaces.Common;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Elements.Common
{
    public class DatePicker : TextField, IDatePicker
    {
        public DatePicker() : this (null) { }
        public DatePicker(By byLocator = null, IWebElement webElement = null)
                : base(byLocator, webElement)
        { }
    }

}
