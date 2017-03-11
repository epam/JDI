using NUnit.Framework;
using System.Collections.Generic;
using JDI_UIWebTests.Enums;
using JDI_Web.Selenium.Elements.Complex;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.Tests.Complex.CommonActionsData;
using static JDI_UIWebTests.UIObjects.TestSite;
using Assert = JDI_Matchers.NUnit.Assert;

namespace JDI_UIWebTests.Tests.Complex
{
    public class RadioButtonsTests
    {
        private static readonly IList<string> OddOptions = new List<string> { "1", "3", "5", "7"};
        private RadioButtons<Odds> OddNumbersControl => MetalsColorsPage.SummaryBlock.OddNumbers;

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
        public void SelectStringTest()
        {
            OddNumbersControl.Select("7");
            CheckAction("Summary (Odd): value changed to 7");
        }

        [Test]
        public void SelectIndexTest()
        {
            OddNumbersControl.Select(4);
            CheckAction("Summary (Odd): value changed to 7");
        }

        [Test]
        [Ignore("C# enum does not support strings")]
        public void SelectEnumTest()
        {
            // Select method is waiting for string "7" not "Seven"
            OddNumbersControl.Select(Odds.Seven);
            CheckAction("Summary (Odd): value changed to 7");
        }

        [Test]
        public void GetOptionsTest()
        {
            Assert.CollectionEquals(OddNumbersControl.Options, OddOptions);
        }

        [Test]
        public void GetNamesTest()
        {
            Assert.CollectionEquals(OddNumbersControl.Names, OddOptions);
        }

        [Test]
        public void GetValuesTest()
        {
            Assert.CollectionEquals(OddNumbersControl.Values, OddOptions);
        }

        [Test]
        public void GetOptionsAsTextTest()
        {
            Assert.AreEquals(OddNumbersControl.OptionsAsText, "1, 3, 5, 7");
        }

        [Test]
        public void SetValueTest()
        {
            OddNumbersControl.Value = "7";
            CheckAction("Summary (Odd): value changed to 7");
        }

        [Test]
        public void GetNameTest()
        {
            Assert.AreEquals(OddNumbersControl.Name, "Odd Numbers");
        }

        [Test]
        public void GetSelectedTest()
        {
            CheckActionThrowError(() => OddNumbersControl.Selected(), NoElementsMessage);
        }

        [Test]
        public void GetSelectedIndexTest()
        {
            CheckActionThrowError(() => OddNumbersControl.SelectedIndex(), NoElementsMessage);
        }

        [Test]
        public void IsSelectedTest()
        {
            CheckActionThrowError(() => OddNumbersControl.Selected("7"), NoElementsMessage);
        }

        [Test]
        [Ignore("C# enum does not support strings")]
        public void IsSelectedEnumTest()
        {
            // Select method is waiting for string "7" not "Seven"
            CheckActionThrowError(() => OddNumbersControl.Selected(Odds.Seven), NoElementsMessage);
        }

        [Test]
        [Ignore("Timer.cs wait method hide exceptions unlike Java")]
        public void WaitSelectedTest()
        {
            CheckActionThrowError(() => OddNumbersControl.WaitSelected("7"), NoElementsMessage);
        }

        [Test]
        [Ignore("Timer.cs wait method hide exceptions unlike Java. C# enum does not support strings")]
        public void WaitSelectedEnumTest()
        {
            CheckActionThrowError(() => OddNumbersControl.WaitSelected(Odds.Seven), NoElementsMessage);
        }

        [Test]
        public void GetValueTest()
        {
            CheckActionThrowError(() => OddNumbersControl.Value.ToString(), NoElementsMessage);
        }
    }
}
