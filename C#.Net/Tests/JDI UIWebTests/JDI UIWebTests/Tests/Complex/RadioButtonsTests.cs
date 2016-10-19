using Epam.JDI.Core.Interfaces.Complex;
using NUnit.Framework;
using System.Collections.Generic;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using JDI_UIWebTests.Enums;
using JDI_Web.Selenium.Elements.Complex;

namespace JDI_UIWebTests.Tests.Complex
{
    public class RadioButtonsTests
    {

        private static readonly IList<string> ODD_OPTIONS = new List<string> { "1", "3", "5", "7"};

        [SetUp]
        public void SetUp()
        {
            Logger.Info("Navigating to Metals and Colors page.");
            MetalsColorsPage.Open();
            MetalsColorsPage.CheckTitle();
            MetalsColorsPage.IsOpened();            
            Logger.Info("Setup method finished");
            Logger.Info("Start test: " + TestContext.CurrentContext.Test.Name);
        }
    }
}
