using Epam.JDI.Core.Interfaces.Complex;
using NUnit.Framework;
using System.Collections.Generic;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using JDI_UIWebTests.Enums;
using static JDI_UIWebTests.Tests.Complex.CommonActionsData;
using JDI_Matchers.NUnit;

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
            CheckAction("Colors: value changed to Blue");
        }

        
        [Test]
        public void SelectIndexTest()
        {
            _colors().Select(4);            
            CheckAction("Colors: value changed to Blue");
        }

        
        [Test]
        public void SelectEnumTest()
        {
            _colors().Select(Colors.Blue);
            CheckAction("Colors: value changed to Blue");
        }

        
        [Test]
        public void GetOptionsTest()
        {
            new Check().CollectionEquals(_colors().Options, COLORS_OPTIONS);
        }

        
        [Test]
        public void GetNamesTest()
        {
            new Check().CollectionEquals(_colors().Names, COLORS_OPTIONS);            
        }

        
        [Test]
        public void GetValuesTest()
        {
            new Check().CollectionEquals(_colors().Values, COLORS_OPTIONS);                        
        }

        
        [Test]
        public void GetOptionsAsTextTest()
        {
            new Check().AreEquals(_colors().OptionsAsText, "Colors, Red, Green, Blue, Yellow");            
        }

        
        [Test]
        public void SetValueTest()
        {
            _colors().Value = "Blue";
            CheckAction("Colors: value changed to Blue");
        }

        
        [Test]
        public void GetNameTest()
        {
            new Check().AreEquals(_colors().Name, "Colors");            
        }

                
        [Test]
        public void GetSelectedTest()
        {
            new Check().AreEquals(_colors().Selected().ToString(), "Colors");            
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
            new Check().AreEquals(_colors().Value, "Colors");            
        }
        
    }
}
