using System;
using System.Linq;
using System.Text.RegularExpressions;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_Commons;
using JDI_Web.Selenium.Base;
using JDI_Web.Settings;
using OpenQA.Selenium;
using RestSharp.Extensions;
using static System.String;
using static Epam.JDI.Core.ExceptionUtils;
using static Epam.JDI.Core.Settings.JDISettings;

namespace JDI_Web.Selenium.Elements.Composite
{
    public class WebPage : WebBaseElement, IPage
    {
        public static bool CheckAfterOpen = false;
        private string _url;

        public string Url
        {
            get => _url == null || _url.Contains("://") || !WebSettings.HasDomain
                ? _url
                : GetUrlFromUri(_url);
            set => _url = value;
        }

        public string Title;
        protected CheckPageTypes CheckUrlType = CheckPageTypes.None;
        protected CheckPageTypes CheckTitleType = CheckPageTypes.None;
        protected string UrlTemplate;
        public static WebPage CurrentPage;

        public WebPage()
        {
        }

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

        public void UpdatePageData(string url, string title, CheckPageTypes checkUrlType, CheckPageTypes checkTitleType,
            string urlTemplate)
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
            if (IsNullOrEmpty(UrlTemplate) && new[] {CheckPageTypes.None, CheckPageTypes.Equal}.Contains(CheckUrlType))
                CheckUrl().Equal();
            else
                switch (CheckUrlType)
                {
                    case CheckPageTypes.None:
                        Asserter.IsTrue(GetUrl().Contains(UrlTemplate)
                                        || GetUrl().Matches(UrlTemplate));
                        break;
                    case CheckPageTypes.Equal:
                        CheckUrl().Equal();
                        break;
                    case CheckPageTypes.Match:
                        CheckUrl().Match();
                        break;
                    case CheckPageTypes.Contains:
                        CheckUrl().Contains();
                        break;
                }
            switch (CheckTitleType)
            {
                case CheckPageTypes.None:
                    CheckTitle().Equal();
                    break;
                case CheckPageTypes.Equal:
                    CheckTitle().Equal();
                    break;
                case CheckPageTypes.Match:
                    CheckTitle().Match();
                    break;
                case CheckPageTypes.Contains:
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

        private bool IsOnPage()
        {
            var url = WebDriver.Url;
            if (IsNullOrEmpty(UrlTemplate)
                && new[] {CheckPageTypes.None, CheckPageTypes.Equal}.Contains(CheckUrlType))
                return url.Equals(Url);
            switch (CheckUrlType)
            {
                case CheckPageTypes.None:
                    return url.Contains(UrlTemplate) || url.Matches(UrlTemplate);
                case CheckPageTypes.Equal:
                    return url.Equals(Url);
                case CheckPageTypes.Match:
                    return url.Matches(UrlTemplate);
                case CheckPageTypes.Contains:
                    return url.Contains(IsNullOrEmpty(UrlTemplate) ? Url : UrlTemplate);
            }
            return false;
        }

        public void IsOpened()
        {
            ActionWithException(() =>
            {
                if (!IsOnPage())
                    Open();
                Logger.Info($"Page {Name} is opened");
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
                if (IsNullOrEmpty(_equals)) return;
                Logger.Info($"Page {_what} equals to '{_equals}'");
                Asserter.IsTrue(_timer().Wait(() => _actual().Equals(_equals)));
            }

            /**
             * Check that current page url/title matches to expected url/title-matcher
             */

            public void Match()
            {
                if (IsNullOrEmpty(_template)) return;
                Logger.Info($"Page {_what} matches to '{_template}'");
                Asserter.IsTrue(_timer().Wait(() => _actual().Matches(_template)));
            }

            /**
             * Check that current page url/title contains expected url/title-matcher
             */

            public void Contains()
            {
                var url = IsNullOrEmpty(_template)
                    ? _equals
                    : _template;
                Logger.Info($"Page {_what} contains to '{url}'");
                Asserter.IsTrue(_timer().Wait(() => _actual().Contains(url)));
            }
        }
    }
}
