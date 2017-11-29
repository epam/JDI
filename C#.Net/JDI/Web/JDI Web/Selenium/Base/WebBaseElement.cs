using System;
using System.Collections.Generic;
using System.Reflection;
using Epam.JDI.Core.Attributes;
using Epam.JDI.Core.Attributes.Functions;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Logging;
using JDI_Commons;
using JDI_Web.Selenium.Attributes;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Elements.APIInteract;
using JDI_Web.Selenium.Elements.WebActions;
using OpenQA.Selenium;
using static System.String;
using static Epam.JDI.Core.Logging.LogLevels;
using static Epam.JDI.Core.Settings.JDISettings;
using JDI_Web.Selenium.Elements.Base;

namespace JDI_Web.Selenium.Base
{
    public class WebBaseElement : IBaseElement
    {
        public By Locator => WebAvatar.ByLocator;
        public By FrameLocator => WebAvatar.FrameLocator;
        private readonly IWebElement _webElement;

        public object Parent { get; set; }

        public WebBaseElement(By byLocator = null, IWebElement webElement = null,
            List<IWebElement> webElements = null, WebBaseElement element = null)
        {
            Invoker = new ActionInvoker(this);
            GetElementClass = new GetElementClass(this);
            Actions = new ElementsActions(this);
            WebAvatar = new GetElementModule(this, byLocator) { WebElement = webElement, WebElements = webElements };
            _webElement = webElement;
            if (element != null)
            {
                WebAvatar.DriverName = element.WebAvatar.DriverName;
                Parent = element.Parent;
            }
        }

        public WebElement GetHighLightElement()
        {
            return Avatar.GetFirstValue<WebElement>();
        }

        public static ActionScenarios ActionScenrios
        {
            set => ActionInvoker.ActionScenrios = value;
        }

        public static Action<string, Action<string>> DoActionRule = (text, action) =>
        {
            if (text == null) return;
            action.Invoke(text);
        };

        public static Action<string, Action<string>> SetValueEmptyAction = (text, action) =>
        {
            if (IsNullOrEmpty(text)) return;
            action.Invoke(text.Equals("#CLEAR#") ? "" : text);
        };

        public Functions Function = Functions.None;

        public void SetFunction(Functions function)
        {
            Function = function;
        }

        public IAvatar Avatar { get; set; }

        public GetElementModule WebAvatar
        {
            get => (GetElementModule) Avatar;
            set => Avatar = value;
        }

        public ActionInvoker Invoker;
        public string Name { get; set; }
        public string ParentTypeName => Parent?.GetType().Name ?? "";
        protected GetElementClass GetElementClass;
        public ElementsActions Actions;
        private string _varName;
        private string VarName => _varName ?? Name;
        private string _typeName;

        public string TypeName
        {
            get => _typeName ?? GetType().Name;
            set => _typeName = value;
        }

        protected Timer Timer => WebAvatar.Timer;

        public void FillLocatorTemplate(string name)
        {
            WebAvatar.ByLocator = Locator.FillByTemplate(name);
        }
        
        public IWebDriver WebDriver => WebAvatar.WebDriver;

        public IWebElement WebElement
        {
            get => _webElement ?? WebAvatar.WebElement;
            set => WebAvatar.WebElement = value;
        }

        public List<IWebElement> WebElements
        {
            get => WebAvatar.WebElements;
            set => WebAvatar.WebElements = value;
        }

        public bool HasLocator => WebAvatar.HasLocator;

        public void SetName(FieldInfo field)
        {
            Name = NameAttribute.GetElementName(field);
            _varName = field.Name;
        }

        public WebBaseElement SetAvatar(GetElementModule avatar = null, By byLocator = null)
        {
            WebAvatar = (avatar ?? WebAvatar).Copy(byLocator);
            return this;
        }

        public void SetWaitTimeout(long mSeconds)
        {
            Logger.Debug("Set wait timeout to " + mSeconds);
            WebDriver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(mSeconds);
            Timeouts.CurrentTimeoutSec = (int) (mSeconds/1000);
        }

        public void RestoreWaitTimeout()
        {
            SetWaitTimeout(Timeouts.WaitElementSec);
        }

        public void DoAction(string actionName, Action action, LogLevels logLevels = Info)
        {
            LogAction(actionName, logLevels);
            action.Invoke();
        }

        public void DoActionResult<TResult>(string actionName, Func<TResult> action,
            Func<TResult, string> logResult = null, LogLevels logLevels = Info)
        {
            LogAction(actionName, logLevels);
            var res = action.Invoke();
            logResult?.Invoke(res);
        }

        public IJavaScriptExecutor JsExecutor => (IJavaScriptExecutor) WebDriver;

        public void LogAction(string actionName, LogLevels level)
        {
            ToLog(Format(ShortLogMessagesFormat
                ? "{0} for {1}"
                : "Perform action '{0}' with WebElement ({1})", actionName, ToString()), level);
        }

        public void LogAction(string actionName)
        {
            LogAction(actionName, Info);
        }

        public new string ToString()
        {
            return ShortLogMessagesFormat
                ? $"{TypeName} '{Name}' ({ParentTypeName}.{VarName}; {Avatar})"
                : $"Name: '{Name}', Type: '{TypeName}' In: '{ParentTypeName}', {Avatar}";
        }
    }
}

