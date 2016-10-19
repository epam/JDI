using NUnit.Framework;
using System.Collections.Generic;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using static JDI_UIWebTests.Enums.Metals;
using JDI_UIWebTests.Enums;
using JDI_Matchers.NUnit;
using static JDI_UIWebTests.Tests.Complex.CommonActionsData;
using JDI_Web.Selenium.Elements.Complex;

namespace JDI_UIWebTests.Tests.Complex
{
    public class ComboBoxTests
    {

        private static readonly IList<string> ODD_OPTIONS = new List<string> { "Col", "Gold", "Silver", "Bronze", "Selen" };

        private ComboBox<Metals> _metals()
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
            CheckAction("Metals: value changed to Gold");
        }

        [Test]
        public void SelectIndexTest()
        {
            _metals().Select(3);
            CheckAction("Metals: value changed to Silver");
        }

        
        [Test]
        public void SelectEnumTest()
        {
            _metals().Select(Gold);
            CheckAction("Metals: value changed to Gold");
        }

        
        [Test]
        public void GetOptionsTest()
        {
            new Check().CollectionEquals(_metals().Options, ODD_OPTIONS);
        }

        
        [Test]
        public void GetNamesTest()
        {
            new Check().CollectionEquals(_metals().Names, ODD_OPTIONS);            
        }

        
        [Test]
        public void GetValuesTest()
        {
            new Check().CollectionEquals(_metals().Values, ODD_OPTIONS);            
        }

        
        [Test]
        public void GetOptionsAsTextTest()
        {
            new Check().AreEquals(_metals().OptionsAsText.ToString(), "Col, Gold, Silver, Bronze, Selen");            
        }

        
        [Test]
        public void SelectValueTest()
        {
            _metals().Select("Silver");            
            CheckAction("Metals: value changed to Silver");
        }

        
        //TO_DO
        /*
        [Test]
        public void GetSelectedTest()
        {
            _metals().Select("Gold");
            new Check().AreEquals(_metals().Selected(), "Gold");            
        }
        
        [Test]
        public void GetSelectedIndexTest()
        {            
            new Check().ThrowException(() => { _metals().SelectedIndex; }, NO_ELEMENTS_MESSAGE);            
        }        
        
        [Test]
        public void IsSelectedTest()
        {            
            IText zxc =  new Text(By.CssSelector(".metals .filter-option"));
            string asd = zxc.GetText;
            string qwe = HomePage.WebDriver.FindElement(By.CssSelector(".metals .filter-option")).Text;
            //new Check().AreEquals(_metals().Selected(), "Col");            
        }
                
        [Test]
        public void IsSelectedEnumTest()
        {
            new Check().IsTrue(_metals().Selected().Equals(Metals.Col));
        }
                
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
                    
        [Test]
        public void WaitSelectedEnumTest()
        {
            new Check("WaitSelected")
                .hasNoExceptions(()->metals().waitSelected(Col));
        }
                
        [Test]
        public void GetValueTest()
        {
            //Assert.True(_metals().Value.Equals("Col"));
            //areEquals(metals().getValue(), "Col");
        }        
        */

    }
}
