using System;
using NUnit.Framework;
using System.Collections.Generic;
using Epam.JDI.Core.Interfaces.Complex;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using static JDI_UIWebTests.Enums.Metals;
using JDI_UIWebTests.Enums;
using JDI_Matchers.NUnit;
using static JDI_UIWebTests.Tests.Complex.CommonActionsData;
using JDI_Web.Settings;
using OpenQA.Selenium;
using Assert = JDI_Matchers.NUnit.Assert;

namespace JDI_UIWebTests.Tests.Complex
{
    public class ComboBoxTests
    {
        private static readonly IList<string> OddOptions = new List<string> { "Col", "Gold", "Silver", "Bronze", "Selen" };

        private IComboBox<Metals> MetalsControl => MetalsColorsPage.ComboBox;

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
            MetalsControl.Select("Gold");
            CheckAction("Metals: value changed to Gold");
        }

        [Test]
        public void SelectIndexTest()
        {
            MetalsControl.Select(3);
            CheckAction("Metals: value changed to Silver");
        }

        [Test]
        public void SelectEnumTest()
        {
            MetalsControl.Select(Gold);
            CheckAction("Metals: value changed to Gold");
        }

        [Test]
        public void GetOptionsTest()
        {
            new Check().CollectionEquals(MetalsControl.Options, OddOptions);
        }

        [Test]
        public void GetNamesTest()
        {
            new Check().CollectionEquals(MetalsControl.Names, OddOptions);
        }

        [Test]
        public void GetValuesTest()
        {
            new Check().CollectionEquals(MetalsControl.Values, OddOptions);
        }

        [Test]
        public void GetOptionsAsTextTest()
        {
            new Check().AreEquals(MetalsControl.OptionsAsText, "Col, Gold, Silver, Bronze, Selen");
        }

        [Test]
        public void SetValueTest()
        {
            MetalsControl.Value = "Blue";
            WebSettings.WebDriver.FindElement(By.ClassName("footer-content")).Click();
            CheckAction("Metals: value changed to Blue");
        }

        [Test]
        public void GetSelectedTest()
        {
            MetalsControl.Select("Gold");
            new Check().AreEquals(MetalsControl.Selected(), "Gold");
        }

        [Test]
        public void GetSelectedIndexTest()
        {
            CheckActionThrowError(() => MetalsControl.SelectedIndex(), NoElementsMessage);
        }

        [Test]
        public void IsSelectedTest()
        {
            Assert.AreEquals(MetalsControl.Selected("Col"), true);
        }

        [Test]
        public void IsSelectedEnumTest()
        {
            Assert.AreEquals(MetalsControl.Selected(Col), true);
        }

        [Test]
        public void WaitSelectedTest()
        {
            try
            {
                MetalsControl.WaitSelected("Col");
            }
            catch (Exception ex)
            {
                throw Exception("WaitSelected throws exception");
            }
        }

        [Test]
        public void WaitSelectedEnumTest()
        {
            new Check("WaitSelected").HasNoException(() => MetalsControl.Value.ToString());
        }
         
        [Test]
        public void GetValueTest()
        {
            Assert.AreEquals(MetalsControl.Value, "Col");
        }        
    }
}
