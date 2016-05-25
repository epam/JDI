using System;
using JDI_Core.Interfaces.Base;
using JDI_Web.Selenium.Base;
using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;

namespace JDI_Web.Selenium.Elements.Base
{
    public class Clickable : WebElement, IClickable
    {
        public Clickable() : this (null) { }
        public Clickable(By byLocator = null, IWebElement webElement = null) 
            : base(byLocator, webElement) { }
        
        protected Action<WebBaseElement> ClickAction = cl => cl.WebElement.Click();
 
        public void Click()
        {
            Actions.Click(ClickAction);
        }

        protected Action<WebBaseElement> ClickJsAction = cl => cl.JsExecutor.ExecuteScript("arguments[0].click();", cl.WebElement);

        public void ClickByXY(int x, int y)
        {
            Invoker.DoJAction($"Click on Element with coordinates (x,y) = ({x},{y})",
            el =>
                {
                    var element = WebElement;
                    new Actions(WebDriver).MoveToElement(WebElement, x, y).Click().Build().Perform();
                }
            );

        }
    }
}
