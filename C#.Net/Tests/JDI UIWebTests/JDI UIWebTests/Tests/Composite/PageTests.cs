using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;
using OpenQA.Selenium;
using JDI_Matchers.NUnit;

namespace JDI_UIWebTests.Tests.Composite
{
    public class PageTests
    {      

        [SetUp]
        public void SetUp()
        {
            Logger.Info("Navigating to Contact page.");
            ContactFormPage.Open();
            ContactFormPage.CheckTitle();
            ContactFormPage.IsOpened();
            Logger.Info("Setup method finished");
            Logger.Info("Start test: " + TestContext.CurrentContext.Test.Name);
        }

        [Test]
        public void RefreshTest()
        {
            ContactFormPage.ContactSubmit.Click();
            new Check().AreEquals(ContactFormPage.Result.GetText, "Summary: 3");            
            ContactFormPage.Refresh();
            new Check().AreEquals(ContactFormPage.Result.GetText, "");            
            ContactFormPage.CheckOpened();            
        }

        
        [Test]
        public void BackTest()
        {
            HomePage.Open();
            HomePage.CheckOpened();
            HomePage.Back();
            ContactFormPage.CheckOpened();            
        }

        
        [Test]
        public void ForwardTest()
        {
            HomePage.Open();
            HomePage.Back();
            ContactFormPage.CheckOpened();
            ContactFormPage.Forward();
            HomePage.CheckOpened();
        }

        [Test]
        public void AddCookieTest()
        {
            HomePage.WebDriver.Manage().Cookies.DeleteAllCookies();
            new Check().IsTrue(HomePage.WebDriver.Manage().Cookies.AllCookies.Count == 0);                                 
            Cookie cookie = new Cookie("key", "value");
            ContactFormPage.AddCookie(cookie);
            new Check().AreEquals(HomePage.WebDriver.Manage().Cookies.GetCookieNamed(cookie.Name).Value, cookie.Value);            
        }


        
        [Test]
        public void ClearCacheTest()
        {
            Cookie cookie = new Cookie("key", "value");
            HomePage.WebDriver.Manage().Cookies.AddCookie(cookie);
            new Check().IsFalse(HomePage.WebDriver.Manage().Cookies.AllCookies.Count == 0);            
            ContactFormPage.ClearCache();
            new Check().IsTrue(HomePage.WebDriver.Manage().Cookies.AllCookies.Count == 0);                                 
        }
        
        [Test]
        public void CheckOpenedTest()
        {
            ContactFormPage.CheckOpened();
        }        

        [TearDown]
        public void TearDown()
        {
            Cookie loginCookie = new Cookie("authUser", "true", "jdi-framework.github.io", "/", null);            
            HomePage.WebDriver.Manage().Cookies.AddCookie(loginCookie);
        }       

    }
}
