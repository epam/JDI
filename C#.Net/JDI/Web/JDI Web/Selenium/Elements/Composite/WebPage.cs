using System;
using System.Text.RegularExpressions;
using JDI_Commons;
using JDI_Core.Interfaces.Complex;
using JDI_Web.Selenium.Base;
using JDI_Web.Settings;
using OpenQA.Selenium;
using RestSharp.Extensions;
using static JDI_Core.ExceptionUtils;
using static JDI_Core.Settings.JDISettings;

namespace JDI_Web.Selenium.Elements.Composite
{
    public class WebPage : WebBaseElement, IPage
    {
        public static bool CheckAfterOpen = false;
        private string _url;
        public string Url {
            get
            {
                return _url == null || _url.Contains("://") || !WebSettings.HasDomain
                        ? _url
                        : GetUrlFromUri(_url); 
            }
            set { _url = value;  }
        }
        public string Title;
        protected CheckPageTypes CheckUrlType = CheckPageTypes.Equal;
        protected CheckPageTypes CheckTitleType = CheckPageTypes.Equal;
        protected string UrlTemplate;
        public static WebPage CurrentPage;

        public WebPage() { }
        public WebPage(string url = null, string title = null)
        {
            Url = url;
            Title = title;
        }

        public static string GetUrlFromUri(string uri)
        {
            return WebSettings.Domain.Replace("/*$", "") + "/" + new Regex("^//*").Replace(uri, "");
        }
        public static string GetMatchFromDomain(string uri)
        {
            return WebSettings.Domain.Replace("/*$", "").Replace(".", "\\.") + "/" + uri.Replace("^/*", "");
        }
        public static void OpenUrl(string url)
        {
            new WebPage(url).Open();
        }

        public static string GetUrl()
        {
            return WebSettings.WebDriver.Url;
        }

        public static string GetTitle()
        {
            return WebSettings.WebDriver.Title;
        }
        public void UpdatePageData(string url, string title, CheckPageTypes checkUrlType, CheckPageTypes checkTitleType, string urlTemplate)
        {
            if (_url == null)
                Url = url;
            if (Title == null)
                Title = title;
            CheckUrlType = checkUrlType;
            CheckTitleType = checkTitleType;
            UrlTemplate = urlTemplate;
        }

        public StringCheckType CheckUrl()
        {
            return new StringCheckType(() => WebDriver.Url, Url, UrlTemplate, "url", () => Timer);
        }

        public StringCheckType CheckTitle()
        {
            return new StringCheckType(() => WebDriver.Title, Title, Title, "title", () => Timer);
        }

        public void CheckOpened()
        {
            switch (CheckUrlType)
            {
                case CheckPageTypes.Equal:
                    CheckUrl().Equal();
                    break;
                case CheckPageTypes.Match:
                    CheckUrl().Match();
                    break;
                case CheckPageTypes.Contain:
                    CheckUrl().Contains();
                    break;
            }
            switch (CheckTitleType)
            {
                case CheckPageTypes.Equal:
                    CheckTitle().Equal();
                    break;
                case CheckPageTypes.Match:
                    CheckTitle().Match();
                    break;
                case CheckPageTypes.Contain:
                    CheckTitle().Contains();
                    break;
            }
        }

        public void Open()
        {
            Invoker.DoJAction($"Open page {Name} by url {Url}",
                el => WebDriver.Navigate().GoToUrl(Url));
            if (CheckAfterOpen)
                CheckOpened();
            CurrentPage = this;
        }

        public void IsOpened()
        {
            ActionWithException(() =>
            {
                Logger.Info($"Page {Name} is opened");
                if (GetUrl().Equals(Url)) return;
                Open();
            }, ex => $"Can't open page {Name}. Reason: {ex}");
        }

        /**
         * Refresh current page
         */
        public void Refresh()
        {
            Invoker.DoJAction($"Refresh page {Name}",
                    el => WebDriver.Navigate().Refresh());
        }

        /**
         * Go back to previous page
         */
        public void Back()
        {
            Invoker.DoJAction("Go back to previous page",
                    el => WebDriver.Navigate().Back());
        }


        /**
         * Go forward to next page
         */
        public void Forward()
        {
            Invoker.DoJAction("Go forward to next page",
                    el => WebDriver.Navigate().Forward());
        }

        /**
         * @param cookie Specify cookie
         *               Add cookie in browser
         */
        public void AddCookie(Cookie cookie)
        {
            Invoker.DoJAction("Go forward to next page",
                    el => WebDriver.Manage().Cookies.AddCookie(cookie));
        }

        /**
         * Clear browsers cache
         */
        public void ClearCache()
        {
            Invoker.DoJAction("Go forward to next page",
                    el => WebDriver.Manage().Cookies.DeleteAllCookies());
        }

        public class StringCheckType
        {
            private readonly Func<string> _actual;
            private readonly string _equals;
            private readonly string _template;
            private readonly string _what;
            private readonly Func<Timer> _timer;

            public StringCheckType(Func<string> actual, string equals, string template, string what, Func<Timer> timer)
            {
                _actual = actual;
                _equals = equals;
                _template = template;
                _what = what;
                _timer = timer;
            }

            /**
             * Check that current page url/title equals to expected url/title
             */
            public void Equal()
            {   
                Logger.Info($"Page {_what} equals to '{_equals}'");
                Asserter.IsTrue(_timer().Wait(() => _actual().Equals(_equals)));
            }

            /**
             * Check that current page url/title matches to expected url/title-matcher
             */
            public void Match()
            {
                Logger.Info($"Page {_what} matches to '{_template}'");
                Asserter.IsTrue(_timer().Wait(() => _actual().Matches(_template)));
            }

            /**
             * Check that current page url/title contains expected url/title-matcher
             */
            public void Contains()
            {
                Logger.Info($"Page {_what} contains to '{_template}'");
                Asserter.IsTrue(_timer().Wait(() => _actual().Contains(_template)));
            }
        }
    }
}
