﻿using System;
using JDI_Commons;
using Epam.JDI.Core.Logging;
using Epam.JDI.Core.Settings;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Base;
using static Epam.JDI.Core.ExceptionUtils;

namespace JDI_Web.Selenium.Elements.WebActions
{
    public class ActionInvoker
    {
        private readonly WebBaseElement _element;
        public static ActionScenrios ActionScenrios = new ActionScenrios();

        public ActionInvoker(WebBaseElement element)
        {
            JDISettings.NewTest();
            _element = element;
        }

        public TResult DoJActionResult<TResult>(string actionName, Func<WebBaseElement, TResult> action,
            Func<TResult, string> logResult = null, LogLevels level = LogLevels.Info)
        {
            return ActionWithException(() =>
            {
                ProcessDemoMode();
                return ActionScenrios.SetElement(_element).ResultScenario(actionName, action, logResult, level);
            }, ex => $"Failed to do '{actionName}' action. Reason: {ex}");
        }
        
        public void DoJAction(string actionName, Action<WebBaseElement> action, LogLevels level = LogLevels.Info)
        {
            TimerExtensions.ForceDone(() => {
                ProcessDemoMode();
                ActionScenrios.SetElement(_element).ActionScenario(actionName, action, level);
            });
        }

        public void ProcessDemoMode()
        {
            if (!JDISettings.IsDemoMode) return;
            if (_element is WebElement)
                ((WebElement)_element).Highlight(JDISettings.HighlightSettings);
        }
    }
}
