using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using static JDI_UIWebTests.Tests.Complex.CommonActionsData;
using Epam.JDI.Core.Interfaces.Common;

namespace JDI_UIWebTests.Tests.Common
{
    public class CheckBoxTests
    {

        private ICheckBox _checkBoxWater = MetalsColorsPage.CbWater;

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
        public void CheckSingleTest() 
        {
            _checkBoxWater.Check();            
            CheckAction("Water: condition changed to true");
        }

    }
}
