using Epam.JDI.Core.Interfaces.Complex;
using NUnit.Framework;
using System.Collections.Generic;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using static JDI_UIWebTests.Enums.Metals;
using JDI_UIWebTests.Enums;

namespace JDI_UIWebTests.Tests.Complex
{
    public class ComboBoxTests
    {

        private static readonly IList<string> ODD_OPTIONS = new List<string> { "Col", "Gold", "Silver", "Bronze", "Selen" };

        private IComboBox<Metals> _metals()
        {
            return MetalsColorsPage.ComboBox;
        }

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
            _metals().Select("Gold");
            //checkAction("Metals: value changed to Gold");
        }

        [Test]
        public void SelectIndexTest()
        {
            _metals().Select(3);
            //checkAction("Metals: value changed to Silver");
        }

        
        [Test]
        public void SelectEnumTest()
        {
            _metals().Select(Gold);
            //checkAction("Metals: value changed to Gold");
        }

        
        [Test]
        public void GetOptionsTest()
        {
            Assert.True(_metals().Options.ToString().Equals(ODD_OPTIONS.ToString()));
            //listEquals(metals().getOptions(), oddOptions);
        }

        
        [Test]
        public void GetNamesTest()
        {
            Assert.True(_metals().Names.ToString().Equals(ODD_OPTIONS.ToString()));
            //listEquals(metals().getNames(), oddOptions);
        }

        
        [Test]
        public void GetValuesTest()
        {
            Assert.True(_metals().Values.ToString().Equals(ODD_OPTIONS.ToString()));
            //listEquals(metals().getValues(), oddOptions);
        }

        
        [Test]
        public void GetOptionsAsTextTest()
        {
            Assert.True(_metals().OptionsAsText.ToString().Equals("Col, Gold, Silver, Bronze, Selen"));
            //areEquals(metals().getOptionsAsText(), "Col, Gold, Silver, Bronze, Selen");
        }

        
        [Test]
        public void SetValueTest()
        {
            _metals().Select("Silver");
            //looseFocus();
            //checkAction("Metals: value changed to Blue");
        }

        
        // Fails
        [Test]
        public void GetSelectedTest()
        {
            _metals().Select("Gold");
            Assert.True(_metals().Selected().Equals("Gold"));
            //areEquals(metals().getSelected(), "Gold");
        }

        
        //TO_DO
        /*
        [Test]
        public void GetSelectedIndexTest()
        {
            Assert.True(_metals().SelectedIndex() == 0);

            //checkActionThrowError(()->metals().getSelectedIndex(), noElementsMessage); // isDisplayed not defined
        }
        */

        //Fails
        [Test]
        public void IsSelectedTest()
        {
            Assert.True(_metals().Selected().Equals("Col"));
            //areEquals(metals().isSelected("Col"), true);
        }

        
        [Test]
    public void IsSelectedEnumTest()
        {
            Assert.True(_metals().Selected().Equals(Metals.Col));
            //areEquals(metals().isSelected(Col), true);
        }

        //Looks like it fails
        [Test]
        public void WaitSelectedTest()
        {
            try
            {
                _metals().WaitSelected("Col");
            }
            catch 
            {
                throw;
            }
        }


        //TO_DO
        /*
        [Test]
        public void WaitSelectedEnumTest()
        {
            new Check("WaitSelected")
                .hasNoExceptions(()->metals().waitSelected(Col));
        }
        */

        //Fails
        [Test]
        public void GetValueTest()
        {
            Assert.True(_metals().Value.Equals("Col"));
            //areEquals(metals().getValue(), "Col");
        }        

    }
}
