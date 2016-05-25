using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using JDI_Commons;
using JDI_Core.Interfaces.Base;
using JDI_Core.Settings;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Base;
using JDI_Web.Settings;
using OpenQA.Selenium;
using static JDI_Core.Settings.JDISettings;

namespace JDI_Web.Selenium.Elements.APIInteract
{
    public class GetElementModule : IAvatar
    {
        public WebBaseElement Element { get; set; }
        public By ByLocator;
        public WebBaseElement RootElement;
        public string DriverName { get; set; }

        public IWebDriver WebDriver 
            => WebSettings.WebDriverFactory.GetDriver(DriverName);
        public Func<IWebElement, bool> LocalElementSearchCriteria;

        public GetElementModule()
        {
            DriverName = JDISettings.DriverFactory.CurrentDriverName;
        }

        public Timer Timer => new Timer(Timeouts.CurrentTimeoutSec*1000);
        public bool HasLocator => ByLocator != null;
        private IWebElement _webElement;
        private List<IWebElement> _webElements;

        public IWebElement WebElement
        {
            get
            {
                Logger.Debug($"Get Web Element: {Element}");
                var element = Timer.GetResultByCondition(GetWebElemetAction, el => el != null);
                Logger.Debug("OneElement found");
                return element;
            }
            set { _webElement = value; }
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
            set { _webElements = value; }
        }
        public T FindImmediately<T>(Func<T> func, T ifError)
        {
            Element.SetWaitTimeout(0);
            var temp = Element.WebAvatar.LocalElementSearchCriteria;
            Element.WebAvatar.LocalElementSearchCriteria = el => true;
            T result;
            try { result = func.Invoke(); }
            catch { result = ifError; }
            Element.WebAvatar.LocalElementSearchCriteria = temp;
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
                    throw Exception(
                        $"Find {result.Count} elements instead of one for Element '{Element}' during {timeout} seconds");
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
            if (element == null || (el = element as WebBaseElement) == null || el.Parent == null)
                return WebDriver.SwitchTo().DefaultContent();
            var locator = el.Locator;
            var searchContext = locator.ContainsRoot()
                ? WebDriver.SwitchTo().DefaultContent()
                : SearchContext(el.Parent);
            locator = locator.ContainsRoot()
                    ? locator.TrimRoot()
                    : locator;
            return locator != null
                ? searchContext.FindElement(CorrectXPath(locator))
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
            return searchContext.FindElements(CorrectXPath(locator)).ToList();
        }

        private By CorrectXPath(By byValue)
        {
            return byValue.ToString().Contains("By.xpath: //")
                    ? byValue.GetByFunc()(new Regex("//").Replace(byValue.GetByLocator(), "./", 1))
                    : byValue;
        }

        private Func<IWebElement, bool> GetSearchCriteria 
            => LocalElementSearchCriteria ?? WebSettings.WebDriverFactory.ElementSearchCriteria;
        
    }
}