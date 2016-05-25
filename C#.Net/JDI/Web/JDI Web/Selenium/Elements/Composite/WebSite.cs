using System;
using JDI_Core.Base;
using JDI_Web.Selenium.Base;
using JDI_Web.Settings;

namespace JDI_Web.Selenium.Elements.Composite
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
