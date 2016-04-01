using System;
using Epam.JDI.Core.Base;
using Epam.JDI.Web.Selenium.Base;
using Epam.JDI.Web.Settings;

namespace Epam.JDI.Web.Selenium.Elements.Composite
{
    public class WebSite : Application
    {
        public static void Init(Type site)
        {
            new WebCascadeInit().InitStaticPages(site, WebSettings.WebDriverFactory.CurrentDriverName);
            CurrentSite = site;
        }
        public static T Init<T>(Type site, string driverName) where T : Application
        {
            return new WebCascadeInit().InitPages<T>(site, driverName);
        }
    }
}
