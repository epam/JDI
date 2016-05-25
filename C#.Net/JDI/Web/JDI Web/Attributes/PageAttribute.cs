using System;
using System.Collections.Generic;
using System.Reflection;
using JDI_Web.Selenium.Elements.Composite;
using JDI_Web.Settings;

namespace JDI_Web.Attributes
{
    [AttributeUsage(AttributeTargets.All, Inherited = false)]
    public class PageAttribute : Attribute
    {
        public string Url           = "";
        public string UrlTemplate   = "";
        public string Title         = "";
        public Dictionary<string, string> UrlParams; 
        public CheckPageTypes CheckType        = CheckPageTypes.Equal;
        public CheckPageTypes UrlCheckType     = CheckPageTypes.Equal;
        public CheckPageTypes TitleCheckType   = CheckPageTypes.Equal;
        
        public static PageAttribute Handler(FieldInfo field)
        {
            return field.GetCustomAttribute<PageAttribute>(false);
        }

        public static PageAttribute Handler(object obj)
        {
            return obj.GetType().GetCustomAttribute<PageAttribute>(false);
        }

        public void FillPage(WebPage page, Type parentClass)
        {
            var url = Url;
            var site = SiteAttribute.Get(parentClass);
            if (!WebSettings.HasDomain && parentClass != null && site != null)
                WebSettings.Domain = site.Domain;
            url = url.Contains("://") || parentClass == null || !WebSettings.HasDomain
                    ? url
                    : WebPage.GetUrlFromUri(url);
            var title = Title;
            var urlTemplate = UrlTemplate;
            if (!string.IsNullOrEmpty(urlTemplate))
                urlTemplate = urlTemplate.Contains("://") || parentClass == null || !WebSettings.HasDomain
                        ? urlTemplate
                        : WebPage.GetMatchFromDomain(urlTemplate);
            var urlCheckType = UrlCheckType;
            var titleCheckType = TitleCheckType;
            if (urlCheckType == CheckPageTypes.Match || urlCheckType == CheckPageTypes.Contain && string.IsNullOrEmpty(urlTemplate))
                urlTemplate = url;
            page.UpdatePageData(url, title, urlCheckType, titleCheckType, urlTemplate);
        }

    }

    public enum PageCheckType { NoCheck, Equal, Contains }
}
