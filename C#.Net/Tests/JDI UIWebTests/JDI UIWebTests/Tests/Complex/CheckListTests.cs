using Epam.JDI.Core.Interfaces.Complex;
using NUnit.Framework;
using System.Collections.Generic;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using JDI_UIWebTests.Enums;

namespace JDI_UIWebTests.Tests.Complex
{
    public class CheckListTests
    {
        private static readonly IList<string> NATURE_OPTIONS = new List<string>{"Water", "Earth", "Wind", "Fire" };           
        private static readonly string ALL_VALUES = "Water, Earth, Wind, Fire";

        private ICheckList<Elements> _nature()
        {
            return MetalsColorsPage.Elements;
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
            _nature().Select("Fire");

            //checkAction("Fire: condition changed to true");
        }

        [Test]
        public void SelectIndexTest()
        {
            _nature().Select(4);
            //checkAction("Fire: condition changed to true");
        }

        [Test]
        public void SelectEnumTest()
        {
            _nature().Select(Elements.Fire);
            //checkAction("Fire: condition changed to true");
        }

        [Test]
        public void Select2StringTest()
        {
            _nature().Select("Water", "Fire");
            //checkAction("Fire: condition changed to true");
            //assertContains(()-> (String) actionsLog.getTextList().get(1), "Water: condition changed to true");
        }

        [Test]
        public void Select2IndexTest()
        {
            _nature().Select(1, 4);
            //checkAction("Fire: condition changed to true");
            //assertContains(()->actionsLog.getTextList().get(1), "Water: condition changed to true");
        }

        [Test]
        public void Select2EnumTest()
        {
            _nature().Select(Elements.Water, Elements.Fire);
            //checkAction("Fire: condition changed to true");
            //assertContains(()->actionsLog.getTextList().get(1), "Water: condition changed to true");
        }

        [Test]
        public void CheckStringTest()
        {
            _nature().Check("Fire");
            //checkAction("Fire: condition changed to true");
        }

        [Test]
        public void CheckIndexTest()
        {
            _nature().Check(4);
            //checkAction("Fire: condition changed to true");
        }

        [Test]
        public void CheckEnumTest()
        {
            _nature().Check(Elements.Fire);
            //checkAction("Fire: condition changed to true");
        }

        [Test]
        public void Check2StringTest()
        {
            _nature().Check("Water", "Fire");
            //checkAction("Fire: condition changed to true");
            //assertContains(()->actionsLog.getTextList().get(1), "Water: condition changed to true");
        }

        [Test]
        public void Check2IndexTest()
        {
            _nature().Check(1, 4);
            //checkAction("Fire: condition changed to true");
            //assertContains(()->actionsLog.getTextList().get(1), "Water: condition changed to true");
        }

        [Test]
        public void Check2EnumTest()
        {
            _nature().Check(Elements.Water, Elements.Fire);
            //checkAction("Fire: condition changed to true");
            //assertContains(()->actionsLog.getTextList().get(1), "Water: condition changed to true");
        }

        [Test]
        public void SelectAllTest()
        {
            _nature().SelectAll();
            /*
            List<String> log = actionsLog.getTextList();
            assertContains(log.get(3), "Water: condition changed to true");
            assertContains(log.get(2), "Earth: condition changed to true");
            assertContains(log.get(1), "Wind: condition changed to true");
            assertContains(log.get(0), "Fire: condition changed to true");
            checkAllChecked();
            */
        }

        [Test]
        public void CheckAllTest()
        {
            _nature().CheckAll();
            /*
            List<String> log = actionsLog.getTextList();
            assertContains(log.get(3), "Water: condition changed to true");
            assertContains(log.get(2), "Earth: condition changed to true");
            assertContains(log.get(1), "Wind: condition changed to true");
            assertContains(log.get(0), "Fire: condition changed to true");
            checkAllChecked();
            */
        }

        [Test]
        public void ClearAllTest()
        {
            _nature().CheckAll();            
            //checkAllChecked();
            //!!!!!!!! Clear doesn't work!!!!!!!!!
            _nature().Clear();
            //checkAllChecked(); // isDisplayed not defined            
        }

        [Test]
        public void UncheckAllTest()
        {
            _nature().CheckAll();
            //checkAllChecked();
            _nature().UncheckAll();
            //!!!!!!!! UncheckAll() doesn't work!!!!!!!!!
            //checkAllChecked(); // isDisplayed not defined
        }

        [Test]
        public void GetOptionsTest()
        {
            Assert.True(_nature().Options.ToString().Equals(NATURE_OPTIONS.ToString()));            
            //listEquals(nature().getOptions(), natureOptions);
        }

        [Test]
        public void GetNamesTest()
        {
            Assert.True(_nature().Names.ToString().Equals(NATURE_OPTIONS.ToString()));
            //listEquals(nature().getNames(), natureOptions);
        }

        [Test]
        public void GetValuesTest()
        {
            Assert.True(_nature().Values.ToString().Equals(NATURE_OPTIONS.ToString()));
            //listEquals(nature().getValues(), natureOptions);
        }

        [Test]
        public void GetOptionsAsTextTest()
        {
            Assert.True(_nature().OptionsAsText.ToString().Equals(ALL_VALUES));
            //areEquals(nature().getOptionsAsText(), allValues);
        }

        [Test]
        public void SetValueTest()
        {
            _nature().Value = "Fire";                
            //checkAction("Fire: condition changed to true");
        }

        [Test]
        public void GetNameTest()
        {
            _nature().Name.Equals("Elements");
            //areEquals(nature().getName(), "Nature");
        }

        [Test]
        public void AreSelectedTest()
        {
            Assert.True(_nature().AreSelected().Count == 0);            
            //listEquals(nature().areSelected(), new ArrayList<>());// isDisplayed not defined
        }


        //TO_DO fix incorrect work of AreSelected method
        [Test]
        public void AreDeselectedTest()
        {
            Assert.True(_nature().AreDeselected().ToString().Equals(NATURE_OPTIONS.ToString()));
            //listEquals(nature().areDeselected(), natureOptions);// isDisplayed not defined
        }

        [Test]
        public void GetValueTest()
        {
            Assert.True(_nature().Value.Equals(""));            
        }
    }
}
