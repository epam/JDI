using Epam.JDI.Core.Attributes;

namespace JDI_UIWebTests.Entities
{
    public class User
    {
        public static User DefaultUser = new User("epam", "1234");

        private string _login;
        private string _password;

        [Name("Login")]
        public string Login
        {
            get => _login;
            set => _login = value;
        }

        [Name("Password")]
        public string Password
        {
            get => _password;
            set => _password = value;
        }

        public User(string login, string password) {
            _login = login;
            _password = password;
        }
    }


}
