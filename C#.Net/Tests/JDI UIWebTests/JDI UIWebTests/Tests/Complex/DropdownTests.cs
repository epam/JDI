using System.Collections.Generic;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_Matchers.NUnit;
using JDI_UIWebTests.Enums;
using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using Assert = JDI_Matchers.NUnit.Assert;

namespace JDI_UIWebTests.Tests.Complex
{
    public class DropdownTests
    {
        private static readonly List<string> OddOptions = new List<string> { "Colors", "Red", "Green", "Blue", "Yellow" };
        private IDropDown<Colors> ColorsControl => MetalsColorsPage.Colors;

        [SetUp]
        public void Setup()
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
            ColorsControl.Select("Blue");
            CommonActionsData.CheckAction("Colors: value changed to Blue");
        }

        [Test]
        public void SelectIndexTest()
        {
            ColorsControl.Select(4);
            CommonActionsData.CheckAction("Colors: value changed to Blue");
        }

        [Test]
        public void SelectEnumTest()
        {
            ColorsControl.Select(Colors.Blue);
            CommonActionsData.CheckAction("Colors: value changed to Blue");
        }

        [Test]
        public void GetOptionsTest()
        {
            Assert.CollectionEquals(ColorsControl.Options, OddOptions);
        }

        [Test]
        public void GetNamesTest()
        {
            Assert.CollectionEquals(ColorsControl.Names, OddOptions);
        }

        [Test]
        public void GetValuesTest()
        {
            Assert.CollectionEquals(ColorsControl.Values, OddOptions);
        }

        [Test]
        public void GetOptionsAsTextTest()
        {
            Assert.AreEquals(ColorsControl.OptionsAsText, "Colors, Red, Green, Blue, Yellow");
        }

        [Test]
        public void SetValueTest()
        {
            ColorsControl.Value = "Blue";
            CommonActionsData.CheckAction("Colors: value changed to Blue");
        }

        [Test]
        public void GetNameTest()
        {
            Assert.AreEquals(ColorsControl.Name, "Colors");
        }

        [Test]
        public void GetSelectedTest()
        {
            Assert.AreEquals(ColorsControl.Selected(), "Colors");
        }

        [Test]
        public void GetSelectedIndexTest()
        {
            CommonActionsData.CheckActionThrowError(() => ColorsControl.SelectedIndex(), CommonActionsData.NoElementsMessage);
        }

        [Test]
        public void IsSelectedTest()
        {
            Assert.AreEquals(ColorsControl.Selected("Colors"), true);
        }

        [Test]
        public void IsSelectedEnumTest()
        {
            Assert.AreEquals(ColorsControl.Selected(Colors.Colors), true);
        }

        [Test]
        public void WaitSelectedTest()
        {
            new Check("WaitSelected").HasNoException(() => ColorsControl.WaitSelected("Colors"));
        }

        [Test]
        public void WaitSelectedEnumTest()
        {
            new Check("WaitSelected").HasNoException(() => ColorsControl.WaitSelected(Colors.Colors));
        }

        [Test]
        public void GetValueTest()
        {
            Assert.AreEquals(ColorsControl.Value, "Colors");
        }
    }
}
