namespace Epam.Tests.Scenarios.Entities
{
    public class User
    {
        public static User Default = new User("UserTest", "Test Password");
        public static User Current;

        public string Name;
        public string Password;

        public User(string name, string password)
        {
            Name = name;
            Password = password;
            Current = this;
        }
    }
}
