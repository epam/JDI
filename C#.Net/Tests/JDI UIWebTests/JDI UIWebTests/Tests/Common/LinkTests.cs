using Epam.JDI.Core.Interfaces.Common;
using JDI_Matchers.NUnit;
using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;

namespace JDI_UIWebTests.Tests.Common
{
    public class LinkTests
    {
        private ILink _link = Footer.About;

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
            _link.Click();
            SupportPage.IsOpened();
        }

        [Test]
        public void GetReferenceTest()
        {
            new Check().AreEquals(_link.GetReference(), SupportPage.Url);            
        }

        /*
        //TO_DO
        [Test]
        public void GetURLTest() 
        {
            
        }

        //TO_DO
        [Test]
        public void WaitReferenceTest()
        {

        }

        //TO_DO
        [Test]
        public void WaitMatchReferenceTest() 
        {

        }
        */
    }
}
