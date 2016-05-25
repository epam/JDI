using System;
using System.Linq;
using JDI_Commons;
using JDI_Core.Interfaces.Base;
using JDI_Core.Logging;
using JDI_Core.Settings;
using JDI_Web.Selenium.Base;
using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;
using static JDI_Core.ExceptionUtils;
using static JDI_Core.Settings.JDISettings;
using static JDI_Web.Settings.WebSettings;

namespace JDI_Web.Selenium.Elements.Base
{
    public class WebElement : WebBaseElement, IElement
    {

        public WebElement() : this (null) { }

        public WebElement(By byLocator = null, IWebElement webElement = null) 
            : base(byLocator, webElement) { }
        
        public static T Copy<T>(T element, By newLocator) where T : WebElement
        {
            return ActionWithException(() => 
            {
                var result = (T)Activator.CreateInstance(element.GetType());
                result.SetAvatar(element.WebAvatar, newLocator);
                return result;
            }, ex => $"Can't copy Element: {element}. Exception: {ex}");
        }

        /**
         * Specified Selenium Element for this Element
         */

        public IWebElement GetWebElement()
        {
            return Invoker.DoJActionResult("Get web element",
                el => WebElement ?? WebAvatar.WebElement, level: LogLevels.Debug);
        }

        public string GetAttribute(string name)
        {
            return GetWebElement().GetAttribute(name);
        }

        public void WaitAttribute(string name, string value)
        {
            Wait(el => el.GetAttribute(name).Equals(value));
        }

        public void SetAttribute(string attributeName, string value)
        {
            Invoker.DoJAction($"Set Attribute '{attributeName}'='{value}'",
                            el => el.JsExecutor.ExecuteScript($"arguments[0].setAttribute('{attributeName}',arguments[1]);",
                                        WebElement, value));
        }

        protected Func<WebBaseElement, bool> IsDisplayedAction = 
            el => el.WebAvatar.FindImmediately(() => el.WebElement.Displayed, false);
        
        public bool Displayed => Actions.IsDisplayed(IsDisplayedAction);
        protected void WaitDisplayedAction()
        {
            Wait(el => el.Displayed);
        }
        public bool Hidden => Actions.IsDisplayed(el => !IsDisplayedAction(el));

        public void WaitDisplayed()
        {
            Actions.WaitDisplayed(el => WebElement.Displayed);
        }

        public void WaitVanished()
        {
            Actions.WaitVanished(el => Timer.Wait(() => !IsDisplayedAction(el)));
        }

        public IWebElement GetInvisibleElement()
        {
            WebAvatar.SearchAll();
            return WebElement;
        }

        /**
         * @param resultFunc Specify expected function result
         * Waits while condition with WebElement happens during specified timeout and returns result using resultFunc
         */
        public void Wait(Func<IWebElement, bool> resultFunc)
        {
            var result = Wait(resultFunc, r => r);
            Asserter.IsTrue(result);
        }

        /**
         * @param resultFunc Specify expected function result
         * @param condition  Specify expected function condition
         * @return Waits while condition with WebElement happens and returns result using resultFunc
         */
        public T Wait<T>(Func<IWebElement, T> resultFunc, Func<T, bool> condition)
        {
            return Timer.GetResultByCondition(() => resultFunc.Invoke(GetWebElement()), condition.Invoke);
        }

        /**
         * @param resultFunc Specify expected function result
         * @param timeoutSec Specify timeout
         * Waits while condition with WebElement happens during specified timeout and returns wait result
         */
        public void Wait(Func<IWebElement, bool> resultFunc, int timeoutSec)
        {
            var result = Wait(resultFunc, r => r, timeoutSec);
            Asserter.IsTrue(result);
        }

        /**
         * @param resultFunc Specify expected function result
         * @param timeoutSec Specify timeout
         * @param condition  Specify expected function condition
         * @return Waits while condition with WebElement and returns wait result
         */
        public T Wait<T>(Func<IWebElement, T> resultFunc, Func<T, bool> condition, int timeoutSec)
        {
            SetWaitTimeout(timeoutSec);
            var result = new Timer(timeoutSec).GetResultByCondition(() => resultFunc.Invoke(GetWebElement()), condition.Invoke);
            RestoreWaitTimeout();
            return result;
        }

        public void Highlight()
        {
            WebDriverFactory.Highlight(this);
        }

        public void Highlight(HighlightSettings highlightSettings)
        {
            WebDriverFactory.Highlight(this, highlightSettings);
        }

        public void ClickWithKeys(params string[] keys)
        {
            Invoker.DoJAction("Ctrl click on Element",
                    el => {
                        var action = new Actions(WebDriver);
                        action = keys.Aggregate(action, (current, key) => current.KeyDown(key));
                        action = action.MoveToElement(WebElement).Click();
                        action = keys.Aggregate(action, (current, key) => current.KeyUp(key));
                        action.Perform();
            });
        }

        public void DoubleClick()
        {
            Invoker.DoJAction("Double click on Element", el => {
                var builder = new Actions(WebDriver);
                builder.DoubleClick(WebElement).Perform();
            });
        }

        public void RightClick()
        {
            Invoker.DoJAction("Right click on Element", el => {
                var builder = new Actions(WebDriver);
                builder.ContextClick(WebElement).Perform();
            });
        }

        public void ClickCenter()
        {
            Invoker.DoJAction("Click in Center of Element", el => {
                var builder = new Actions(WebDriver);
                builder.Click(WebElement).Perform();
            });
        }

        public void MouseOver()
        {
            Invoker.DoJAction("Move mouse over Element", el => {
                var builder = new Actions(WebDriver);
                builder.MoveToElement(WebElement).Build().Perform();
            });
        }

        public void Focus()
        {
            Invoker.DoJAction("Focus on Element", el => {
               var size = WebElement.Size;
                new Actions(WebDriver).MoveToElement(WebElement, size.Width / 2, size.Height / 2).Build().Perform();
            });
        }

        public void SelectArea(int x1, int y1, int x2, int y2)
        {
            Invoker.DoJAction($"Select area: from ({x1},{y1}) to ({x2},{y2})", el => {
                var element = WebElement;
                new Actions(WebDriver).MoveToElement(element, x1, y1).ClickAndHold()
                        .MoveToElement(WebElement, x2, y2).Release().Build().Perform();
            });
        }

        public void DragAndDrop(int x, int y)
        {
            Invoker.DoJAction($"Drag and drop Element: (x,y)=({x},{y})", el =>
            new Actions(WebDriver).DragAndDropToOffset(WebElement, x, y).Build().Perform());
        }
    }
    
}