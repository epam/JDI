using Epam.JDI.Core.Interfaces.Complex;
using NUnit.Framework;
using System.Collections.Generic;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using JDI_UIWebTests.Enums;

namespace JDI_UIWebTests.Tests.Complex
{
    public class DropdownExpandedTests
    {
        private static readonly IList<string> COLORS_OPTIONS = new List<string> { "Colors", "Red", "Green", "Blue", "Yellow" };

        private IDropDown<Colors> _colors()
        {
            return MetalsColorsPage.Colors;
        }

        [SetUp]
        public void SetUp()
        {
            Logger.Info("Navigating to Metals and Colors page.");
            MetalsColorsPage.Open();
            MetalsColorsPage.CheckTitle();
            MetalsColorsPage.IsOpened();
            _colors().Expand();
            Logger.Info("Setup method finished");
            Logger.Info("Start test: " + TestContext.CurrentContext.Test.Name);
        }

        [Test]
        public void SelectStringTest()
        {
            _colors().Select("Blue");
            //checkAction("Colors: value changed to Blue");
        }

        
        [Test]
        public void SelectIndexTest()
        {
            _colors().Select(4);
            //checkAction("Colors: value changed to Blue");
        }

        
        [Test]
        public void SelectEnumTest()
        {
            _colors().Select(Colors.Blue);
            //checkAction("Colors: value changed to Blue");
        }

        
        [Test]
        public void GetOptionsTest()
        {
            Assert.True(_colors().Options.ToString().Equals(COLORS_OPTIONS.ToString()));
            //listEquals(colors().getOptions(), oddOptions);
        }

        
        [Test]
        public void GetNamesTest()
        {
            Assert.True(_colors().Names.ToString().Equals(COLORS_OPTIONS.ToString()));
            //listEquals(colors().getNames(), oddOptions);
        }

        
        [Test]
        public void GetValuesTest()
        {
            Assert.True(_colors().Values.ToString().Equals(COLORS_OPTIONS.ToString()));
            //listEquals(colors().getValues(), oddOptions);
        }

        
        [Test]
        public void GetOptionsAsTextTest()
        {
            Assert.True(_colors().OptionsAsText.Equals("Colors, Red, Green, Blue, Yellow"));
            //areEquals(colors().getOptionsAsText(), "Colors, Red, Green, Blue, Yellow");
        }

        
        [Test]
        public void SetValueTest()
        {
            _colors().Value = "Blue";
            //checkAction("Colors: value changed to Blue");
        }

        
        [Test]
        public void GetNameTest()
        {
            Assert.True(_colors().Name.Equals("Colors"));
            //areEquals(colors().getName(), "Colors");
        }

                
        [Test]
        public void GetSelectedTest()
        {
            Assert.True(_colors().Selected().ToString().Equals("Colors"));
            //areEquals(colors().getSelected(), "Colors");
        }

        //TO_DO
        /*
        [Test]
        public void GetSelectedIndexTest()
        {

            //checkActionThrowError(()->colors().getSelectedIndex(), noElementsMessage); // isDisplayed not defined
        }
                    

        [Test]
        public void WaitSelectedTest()
        {
            new Check("WaitSelected")
                    .hasNoExceptions(()->colors().waitSelected("Colors"));
        }
        

        [Test]
        public void WaitSelectedEnumTest()
        {
            new Check("WaitSelected")
                    .hasNoExceptions(()->colors().waitSelected(Colors));
        }
        */

        
        [Test]
        public void GetValueTest()
        {
            Assert.True(_colors().Value.Equals("Colors"));
            //areEquals(colors().getValue(), "Colors");
        }
        
    }
}
