using System;
using System.Collections.Generic;
using System.Reflection;
using Epam.JDI.Commons;
using Epam.JDI.Core.Attributes;
using Epam.JDI.Core.Attributes.Functions;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Logging;
using Epam.JDI.Core.Settings;
using Epam.JDI.Web.Selenium.Attributes;
using Epam.JDI.Web.Selenium.Elements.APIInteract;
using Epam.JDI.Web.Selenium.Elements.WebActions;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Base
{
    public class WebBaseElement : IBaseElement
    {
        public By Locator => WebAvatar.ByLocator;
        public By Frame;
        private IWebElement _webElement;

        public object Parent { get; set; }
        

        public static ActionScenrios ActionScenrios
        {
            set { ActionInvoker.ActionScenrios = value; }
        }

        public static Action<string, Action<string>> DoActionRule = (text, action) => {
            if (text == null) return;
            action.Invoke(text);
        };
        public static Action<string, Action<string>> SetValueEmptyAction = (text, action) => {
            if (text == null || text.Equals("")) return;
            action.Invoke(text.Equals("#CLEAR#") ? "" : text);
        };
        public Functions Function = Functions.None;
        public void SetFunction(Functions function) { Function = function; }
        public IAvatar Avatar { get; set; }
        public GetElementModule WebAvatar {
            get { return (GetElementModule) Avatar; }
            set { Avatar = value;  }
        }
        public ActionInvoker Invoker;
        public string Name { get; set; }
        public string ParentTypeName => Parent?.GetType().Name ?? "";
        protected GetElementClass GetElementClass;
        public ElementsActions Actions;
        private string _varName;
        private string VarName => _varName ?? Name;
        private string _typeName;
        public string TypeName {
            get { return _typeName ?? GetType().Name; }
            set { _typeName = value;  }
        }
        protected Timer Timer => WebAvatar.Timer;

        public WebBaseElement() : this(null)
        {
        }
        
        public WebBaseElement(By byLocator = null, IWebElement webElement = null)
        {
            Invoker = new ActionInvoker(this);
            GetElementClass = new GetElementClass(this);
            Actions = new ElementsActions(this);
            _webElement = webElement;
            Avatar = new GetElementModule
            {
                Element = this,
                ByLocator = byLocator
            };
        }

        public IWebDriver WebDriver => WebAvatar.WebDriver;

        public IWebElement WebElement
        {
            get { return _webElement ?? WebAvatar.WebElement; }
            set { WebAvatar.WebElement = value; }
        }

        protected List<IWebElement> WebElements
        {
            get { return WebAvatar.WebElements; }
            set { WebAvatar.WebElements = value; }
        }
        public bool HasLocator => WebAvatar.HasLocator;

        public void SetName(FieldInfo field)
        {
            Name = NameAttribute.GetElementName(field);
            _varName = field.Name;

        }
        public WebBaseElement SetAvatar(GetElementModule avatar, By byLocator = null)
        {
            Avatar = byLocator != null
                ? new GetElementModule
                {
                    Element = this,
                    ByLocator = byLocator,
                    LocalElementSearchCriteria = avatar.LocalElementSearchCriteria,
                    DriverName = avatar.DriverName
                }
                : avatar;
            return this;
        }
        public void SetWaitTimeout(long mSeconds)
        {
            JDISettings.Logger.Debug("Set wait timeout to " + mSeconds);
            WebDriver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromMilliseconds(mSeconds));
            JDISettings.Timeouts.CurrentTimeoutSec = (int)(mSeconds / 1000);
        }
        public void RestoreWaitTimeout()
        {
            SetWaitTimeout(JDISettings.Timeouts.WaitElementSec);
        }

        public void DoAction(string actionName, Action action, LogLevels logLevels = LogLevels.Info)
        {

        }

        public void DoActionResult<TResult>(string actionName, Func<TResult> action,
            Func<TResult, string> logResult = null, LogLevels logLevels = LogLevels.Info)
        {
        }
        public IJavaScriptExecutor JsExecutor => (IJavaScriptExecutor) WebDriver;

        public void LogAction(string actionName, LogLevels level)
        {
            JDISettings.ToLog(string.Format(JDISettings.ShortLogMessagesFormat
                    ? "{0} for {1}"
                    : "Perform action '{0}' with WebElement ({1})", actionName, ToString()), level);
        }
        public void LogAction(string actionName)
        {
            LogAction(actionName, LogLevels.Info);
        }
        public new string ToString()
        {
            return JDISettings.ShortLogMessagesFormat
                        ? $"{TypeName} '{Name}' ({ParentTypeName}.{VarName}; {Avatar})"
                        : $"Name: '{Name}', Type: '{TypeName}' In: '{ParentTypeName}', {Avatar}";
        }

    }
}

