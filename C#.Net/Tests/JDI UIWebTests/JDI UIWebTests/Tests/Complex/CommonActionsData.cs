using Epam.JDI.Core.Interfaces.Base;
using NUnit.Framework;
using static JDI_UIWebTests.UIObjects.TestSite;
using System.Collections.Generic;

namespace JDI_UIWebTests.Tests.Complex
{
    public class CommonActionsData
    {

        //to check result of calculation on metals and colors page
        public static void CheckCalculate(string text)
        {
            StringAssert.Contains(MetalsColorsPage.CalculateText.GetText, text);
        }

        public static void CheckText(IElement element, string attrName, string expectedAttrValue)
        {
            Assert.True(element.GetAttribute(attrName).Equals(expectedAttrValue));
        }

        public static void CheckAction(string text)            
        {
            IList<string> logOutput = ActionsLog.LogList.Texts;
            Assert.True(logOutput[0].Contains(text));
        }


    }
}
