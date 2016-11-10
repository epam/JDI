using Epam.JDI.Core.Interfaces.Base;
using static JDI_UIWebTests.UIObjects.TestSite;
using System.Collections.Generic;
using JDI_Matchers.NUnit;
using OpenQA.Selenium;

namespace JDI_UIWebTests.Tests.Complex
{
    public class CommonActionsData
    {
        public static readonly string NO_ELEMENTS_MESSAGE = "No elements selected. Override getSelectedAction or place locator to <select> tag";
        
        //to check result of calculation on metals and colors page
        public static void CheckCalculate(string text)
        {
            new Check().Contains(MetalsColorsPage.CalculateText.GetText, text);            
        }

        public static void CheckText(IElement element, string attrName, string expectedAttrValue)
        {
            new Check().AreEquals(element.GetAttribute(attrName), expectedAttrValue);            
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
    }
}
