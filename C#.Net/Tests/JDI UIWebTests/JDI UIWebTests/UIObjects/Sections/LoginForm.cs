using Epam.JDI.Core.Attributes;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Composite;
using JDI_UIWebTests.Entities;

namespace JDIWebTests.UIObjects.Sections
{
    public class LoginForm : Form<User>
    {
        [FindBy(Css = "a>div.profile-photo")]
        private IClickable profile;

        [FindBy(Css = "button.btn-login")]
        private IButton loginButton;

        [FindBy(Id = "Login")]
        [Name("Login")]
        private ITextField loginField;

        [FindBy(Id = "Password")]
        [Name("Password")]
        private ITextField passwordField;

        public void Submit(User user)
        {
            profile.Click();
            base.Submit(user);
        }
    }
}