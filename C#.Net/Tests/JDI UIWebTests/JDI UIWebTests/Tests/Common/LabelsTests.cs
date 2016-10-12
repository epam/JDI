using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using JDI_UIWebTests.Tests.Complex;
using static JDI_UIWebTests.UIObjects.TestSite;


namespace JDI_UIWebTests.Tests.Common
{
    [TestFixture]
    public class LabelsTests
    {
               

        [SetUp]
        public void SetUp() {                    
            MetalsColorsPage.Open();
            Logger.Info("Navigating to Metals and Colors page.");
            MetalsColorsPage.CheckTitle();
            Logger.Info("Setup method finished");

        }

        [Test]
        public void CheckCalculate() {
            MetalsColorsPage.CalculateButton.Click();
            CommonActionsData.CheckCalculate("Summary: 3");
        }
    }
}
