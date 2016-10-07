using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using Epam.JDI.Core.Interfaces.Common;
using JDI_UIWebTests.Tests.Complex;

namespace JDI_UIWebTests.Tests.Common
{
    public class ButtonTests
    {
        private IButton _button = MetalsColorsPage.CalculateButton;

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

        [Test]
        public void ClickTest()
        {
            _button.Click();
            CommonActionsData.CheckCalculate("Summary: 3");
        }
    }
}
