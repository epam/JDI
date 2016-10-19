using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using Epam.JDI.Core.Interfaces.Common;
using JDI_Matchers.NUnit;

namespace JDI_UIWebTests.Tests.Common
{
    public class ImagesTests
    {
        private IImage _logoImage = HomePage.LogoImage;
        private const string ALT = "ALT";
        private const string SRC = "https://jdi-framework.github.io/tests/images/Logo_Epam_Color.svg";

        [SetUp]
        public void SetUp()
        {
            Logger.Info("Navigating to Metals and Colors page.");
            HomePage.Open();
            HomePage.CheckTitle();
            HomePage.IsOpened();
            Logger.Info("Setup method finished");
            Logger.Info("Start test: " + TestContext.CurrentContext.Test.Name);
        }

        [Test]
        public void ClickTest()
        {
            ContactFormPage.Open();
            _logoImage.Click();
            HomePage.IsOpened();            
        }

        [Test]
        public void SetAttributeTest()
        {
            string _attributeName = "testAttr";
            string _value = "testValue";
            _logoImage.SetAttribute(_attributeName, _value);
            new Check().AreEquals(_logoImage.GetAttribute(_attributeName), _value);                
        }

        [Test]
        public void GetSourceTest()
        {
            new Check().AreEquals(_logoImage.GetSource(), SRC);            
        }

        [Test]
        public void GetTipTest()
        {
            new Check().AreEquals(_logoImage.GetAlt(), ALT);            
        }
    }
}
