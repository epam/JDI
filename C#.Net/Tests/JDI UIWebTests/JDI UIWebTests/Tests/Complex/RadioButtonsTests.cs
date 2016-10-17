using Epam.JDI.Core.Interfaces.Complex;
using NUnit.Framework;
using System.Collections.Generic;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using JDI_UIWebTests.Enums;
using JDI_Web.Selenium.Elements.Complex;

namespace JDI_UIWebTests.Tests.Complex
{
    public class RadioButtonsTests
    {

        private static readonly IList<string> ODD_OPTIONS = new List<string> { "1", "3", "5", "7"};

        private Selector<Odds> _odds()
        {
            return MetalsColorsPage.Summary.Odds;
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
        public void SelectStringTest() {
            _odds().Select("7");
            //odds().select("7");
            //checkAction("Summary (Odd): value changed to 7");
        }

        /*
    @Test
    public void selectIndexTest() {
        odds().select(4);
        checkAction("Summary (Odd): value changed to 7");
    }

    @Test
    public void selectEnumTest() {
        odds().select(SEVEN);
        checkAction("Summary (Odd): value changed to 7");
    }

    @Test
    public void getOptionsTest() {
        listEquals(odds().getOptions(), oddOptions);
    }

    @Test
    public void getNamesTest() {
        listEquals(odds().getNames(), oddOptions);
    }

    @Test
    public void getValuesTest() {
        listEquals(odds().getValues(), oddOptions);
    }

    @Test
    public void getOptionsAsTextTest() {
        areEquals(odds().getOptionsAsText(), "1, 3, 5, 7");
    }

    @Test
    public void setValueTest() {
        odds().setValue("7");
        checkAction("Summary (Odd): value changed to 7");
    }

    @Test
    public void getNameTest() {
        areEquals(odds().getName(), "OddsR");
    }

    // Fails
    @Test
    public void getSelectedTest() {
        checkActionThrowError(() -> odds().getSelected(), noElementsMessage); // isDisplayed not defined
    }

    @Test
    public void getSelectedIndexTest() {
        checkActionThrowError(() -> odds().getSelectedIndex(), noElementsMessage); // isDisplayed not defined
    }

    @Test
    public void isSelectedTest() {
        checkActionThrowError(() -> odds().isSelected("7"), noElementsMessage); // isDisplayed not defined
    }

    @Test
    public void isSelectedEnumTest() {
        checkActionThrowError(() -> odds().isSelected(SEVEN), noElementsMessage); // isDisplayed not defined
    }

    @Test
    public void waitSelectedTest() {
        checkActionThrowError(() -> odds().waitSelected("7"), noElementsMessage); // isDisplayed not defined
    }

    @Test
    public void waitSelectedEnumTest() {
        checkActionThrowError(() -> odds().waitSelected(SEVEN), noElementsMessage); // isDisplayed not defined
    }

    @Test
    public void getValueTest() {
        checkActionThrowError(() -> odds().getValue(), noElementsMessage); // isDisplayed not defined
    }*/
    }
}
