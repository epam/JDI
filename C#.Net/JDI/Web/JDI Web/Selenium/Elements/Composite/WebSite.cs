using System;
using Epam.JDI.Core.Base;
using JDI_Web.Selenium.Base;
using JDI_Web.Settings;
using OpenQA.Selenium;
using JDI_Web.Selenium.DriverFactory;

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
        public static T Init<T>(Type site, DriverTypes driverType = DriverTypes.Chrome) where T : Application
        {
            return Init<T>(site, WebSettings.UseDriver(driverType));
        }

        public T Init<T>(string driverName) where T : Application
        {
            DriverName = driverName;
            return Init<T>(GetType(), driverName);
        }
        public T Init<T>(DriverTypes driverType = DriverTypes.Chrome) where T : Application
        {
            DriverName = WebSettings.UseDriver(driverType);
            return Init<T>(DriverName);
        }

        public T InitScope<T>(object scope) where T : Application
        {
            return Init<T>(GetType());
        }

        public IWebDriver WebDriver => WebSettings.WebDriverFactory.GetDriver(DriverName);
        public void OpenUrl(string url) { WebDriver.Navigate().GoToUrl(url); }
        public static void OpenURL(string url) { WebSettings.WebDriverFactory.GetDriver().Navigate().GoToUrl(url); }
    }
}
