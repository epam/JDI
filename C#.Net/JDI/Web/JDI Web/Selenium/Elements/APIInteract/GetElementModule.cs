using System;
using System.Collections.Generic;
using System.Linq;
using JDI_Commons;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Settings;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Base;
using JDI_Web.Settings;
using OpenQA.Selenium;
using static System.String;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_Web.Settings.WebSettings;
using WebDriverFactory = JDI_Web.Selenium.DriverFactory.WebDriverFactory;

namespace JDI_Web.Selenium.Elements.APIInteract
{
    public class GetElementModule : IAvatar
    {
        public WebBaseElement Element { get; set; }
        public By ByLocator;
        public By FrameLocator;
        public WebBaseElement RootElement;
        public string DriverName { get; set; }

        public IWebDriver WebDriver 
            => WebSettings.WebDriverFactory.GetDriver(DriverName);
        public Func<IWebElement, bool> LocalElementSearchCriteria;

        public GetElementModule(WebBaseElement element, By byLocator = null)
        {
            Element = element;
            ByLocator = byLocator;
            if (IsNullOrEmpty(DriverName) && WebSettings.WebDriverFactory != null && !IsNullOrEmpty(WebSettings.WebDriverFactory.CurrentDriverName))
                DriverName = WebSettings.WebDriverFactory.CurrentDriverName;
        }

        public Timer Timer => new Timer(Timeouts.CurrentTimeoutSec*1000);
        public bool HasLocator => ByLocator != null;
        private IWebElement _webElement;
        private List<IWebElement> _webElements;

        public GetElementModule Copy()
        {
            return Copy(ByLocator);
        }

        public GetElementModule Copy(By byLocator)
        {
            var clone = new GetElementModule(Element, byLocator)
            {
                LocalElementSearchCriteria = LocalElementSearchCriteria,
                FrameLocator = FrameLocator,
                RootElement = RootElement,
                DriverName = DriverName,
                Element = Element,
                WebElement = _webElement,
                WebElements = _webElements
            };
            return clone;
        }
        
        public IWebElement WebElement
        {
            get
            {
                Logger.Debug($"Get Web Element: {Element}");
                var element = Timer.GetResultByCondition(GetWebElemetAction, el => el != null);
                Logger.Debug("OneElement found");
                return element;
            }
            set => _webElement = value;
        }

        public List<IWebElement> WebElements
        {
            get
            {
                Logger.Debug($"Get Web Elements: {Element}");
                var elements = GetWebElemetsAction();
                Logger.Debug($"Found {elements.Count} elements");
                return elements;
            }
            set => _webElements = value;
        }
        public T FindImmediately<T>(Func<T> func, T ifError)
        {
            Element.SetWaitTimeout(0);
            var temp = LocalElementSearchCriteria;
            LocalElementSearchCriteria = el => true;
            T result;
            try { result = func.Invoke(); }
            catch { result = ifError; }
            LocalElementSearchCriteria = temp;
            Element.RestoreWaitTimeout();
            return result;
        }

        private IWebElement GetWebElemetAction()
        {
            if (_webElement != null)
                return _webElement;
            var timeout = Timeouts.CurrentTimeoutSec;
            var result = GetWebElemetsAction();
            switch (result.Count)
            {
                case 0:
                    throw Exception($"Can't find Element '{Element}' during {timeout} seconds");
                case 1:
                    return result[0];
                default:
                    if (WebDriverFactory.OnlyOneElementAllowedInSearch)
                        throw Exception(
                            $"Find {result.Count} elements instead of one for Element '{Element}' during {timeout} seconds");
                    return result[0];
            }

        }
        private List<IWebElement> GetWebElemetsAction()
        {
            if (_webElements != null)
                return _webElements;
            var result = Timer.GetResultByCondition(
                    SearchElements,
                    els => els.Count(GetSearchCriteria) > 0);
            Timeouts.DropTimeouts();
            if (result == null)
                throw Exception("Can't get Web Elements");
            return result.Where(GetSearchCriteria).ToList();

        }

        private ISearchContext SearchContext(object element)
        {
            WebBaseElement el;
            if (element == null || (el = element as WebBaseElement) == null 
                || el.Parent == null && el.FrameLocator == null)
                return WebDriver.SwitchTo().DefaultContent();
            var elem = element as WebElement;
            if (elem?.WebAvatar._webElement != null)
                return elem.WebElement;
            var locator = el.Locator;
            var searchContext = locator.ContainsRoot()
                ? WebDriver.SwitchTo().DefaultContent()
                : SearchContext(el.Parent);
            locator = locator.ContainsRoot()
                    ? locator.TrimRoot()
                    : locator;
            var frame = el.WebAvatar.FrameLocator;
            if (frame != null)
                WebDriver.SwitchTo().Frame(WebDriver.FindElement(frame));
            return locator != null
                ? searchContext.FindElement(locator.CorrectXPath())
                : searchContext;
        }

        public GetElementModule SearchAll()
        {
            LocalElementSearchCriteria = el => el != null;
            return this;
        }

        private List<IWebElement> SearchElements()
        {
            var searchContext = ByLocator.ContainsRoot()
                ? WebDriver.SwitchTo().DefaultContent()
                : SearchContext(Element.Parent);
            var locator = ByLocator.ContainsRoot()
                    ? ByLocator.TrimRoot()
                    : ByLocator;
            return searchContext.FindElements(locator.CorrectXPath()).ToList();
        }

        private Func<IWebElement, bool> GetSearchCriteria 
            => LocalElementSearchCriteria ?? WebSettings.WebDriverFactory.ElementSearchCriteria;
    }
}