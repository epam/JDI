using Epam.JDI.Core.Attributes;

namespace JDI_UIWebTests.Entities
{
    public class User
    {
        public static User DefaultUser = new User("epam", "1234");

        [Name("Login")]
        public string Login { get; set; }

        [Name("Password")]
        public string Password { get; set; }

        public User(string login, string password)
        {
            Login = login;
            Password = password;
        }
    }


}
