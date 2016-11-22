using System;
using static JDI_UIWebTests.UIObjects.TestSite;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using Epam.JDI.Core.Settings;
using JDI_Matchers.NUnit;
using OpenQA.Selenium;

namespace JDI_UIWebTests.Tests.Complex
{
    public class CommonActionsData
    {
        public static string NoElementsMessage => "No elements selected. Override getSelectedAction or place locator to <select> tag";
        public static int WaitTimeout => 1000;

        /// <summary>
        /// Check result of calculation on "Metals and Colors" page
        /// </summary>
        /// <param name="text"></param>
        public static void CheckCalculate(string text)
        {
            new Check().Contains(MetalsColorsPage.CalculateText.GetText, text);            
        }

        public static void CheckText(Func<string> func, string expectedAttrValue)
        {
            Assert.AreEquals(func(), expectedAttrValue);
        }

        public static void CheckAction(string text)            
        {            
            IList<IWebElement> logOutput = HomePage.WebDriver.FindElements(By.CssSelector(".logs li"));
            new Check().Contains(logOutput[0].Text, text);            
        }

        public static void CheckResult(string text)
        {
            new Check().Contains(ContactFormPage.Result.GetText, text);              
        }

        public static void CheckActionThrowError(Action checkedAction, string message)
        {
            try
            {
                checkedAction();
            }
            catch (Exception ex)
            {
                Assert.Contains(ex.Message, message);
                return;
            }
            throw JDISettings.Exception("Exception not thrown");
        }

        public static void RunParallel(Action action)
        {
            Task.Run(() =>
            {
                Thread.Sleep(WaitTimeout);
                action();
            });
        }
    }
}
