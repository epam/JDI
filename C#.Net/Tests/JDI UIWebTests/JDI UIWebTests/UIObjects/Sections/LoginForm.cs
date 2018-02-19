using Epam.JDI.Core.Attributes;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Common;
using JDI_UIWebTests.Entities;
using JDI_Web.Selenium.Elements.Composite;
using OpenQA.Selenium.Support.PageObjects;

namespace JDI_UIWebTests.UIObjects.Sections
{
    public class LoginForm : Form<User>
    {
        [FindsBy(How = How.CssSelector, Using = "a>div.profile-photo")]
        private IClickable profile;

        [FindsBy(How = How.CssSelector, Using = "button.btn-login")]
        private IButton loginButton;

        [FindsBy(How = How.Id, Using = "Login")]
        [Name("Login")]
        private ITextField loginField;

        [FindsBy(How = How.Id, Using = "Password")]
        [Name("Password")]
        private ITextField passwordField;

        public new void Submit(User user)
        {
            profile.Click();
            base.Submit(user);
        }
    }
}