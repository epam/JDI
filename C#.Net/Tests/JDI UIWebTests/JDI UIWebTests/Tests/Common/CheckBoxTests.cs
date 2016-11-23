using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using static JDI_UIWebTests.Tests.Complex.CommonActionsData;
using Epam.JDI.Core.Interfaces.Common;
using JDI_UIWebTests.DataProviders;
using Assert = JDI_Matchers.NUnit.Assert;

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

        [Test]
        public void UncheckSingleTest()
        {
            _checkBoxWater.Click();
            _checkBoxWater.Uncheck();
            CheckAction("Water: condition changed to false");
        }

        [Test]
        public void IsCheckTest()
        {
            Assert.IsFalse(_checkBoxWater.IsChecked());
            _checkBoxWater.Click();
            Assert.IsTrue(_checkBoxWater.IsChecked());
        }

        [Test]
        public void MultipleUncheckTest()
        {
            _checkBoxWater.Click();
            _checkBoxWater.Uncheck();
            _checkBoxWater.Uncheck();
            CheckAction("Water: condition changed to false");
        }

        [Test]
        public void ClickTest()
        {
            _checkBoxWater.Click();
            CheckAction("Water: condition changed to true");
            _checkBoxWater.Click();
            CheckAction("Water: condition changed to false");
        }

        [Test]
        [TestCaseSource(typeof(CheckBoxProvider), nameof(CheckBoxProvider.InputData))]
        public void SetValueTest(string value, bool expected)
        {
            if (!expected)
            {
                _checkBoxWater.Click();
            }
            _checkBoxWater.Value = value;
            string resultMsg = "Water: condition changed to " + expected.ToString().ToLower();
            CheckAction(resultMsg);
        }

        [Test]
        [TestCaseSource(typeof(CheckBoxProvider), nameof(CheckBoxProvider.InputValue))]
        public void SetWrongValueTest(string value)
        {
            _checkBoxWater.Click();
            _checkBoxWater.Value = value;
            CheckAction("Water: condition changed to true");
            _checkBoxWater.Click();
            _checkBoxWater.Value = value;
            CheckAction("Water: condition changed to false");
        }
    }
}
