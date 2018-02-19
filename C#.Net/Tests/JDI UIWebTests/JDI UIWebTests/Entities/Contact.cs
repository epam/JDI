using System.Collections.Generic;

namespace JDI_UIWebTests.Entities
{
    public class Contact
    {
        public static readonly Contact DEFAULT_CONTACT = new Contact("Test", "Testov", "Test description", 3, 4);

        public string FirstName { get; set; }

        public string LastName { get; set; }

        public string Description { get; set; }

        public int FirstSummary { get; set; }

        public int SecondSummary { get; set; }

        public Contact(string firstName, string lastName, string description, int firstSummary, int secondSummary)
        {
            FirstName = firstName;
            LastName = lastName;
            Description = description;
            FirstSummary = firstSummary;
            SecondSummary = secondSummary;
        }

        public IList<string> ToList()
        {
            return new List<string> { FirstName, LastName, Description };
        }

        public override string ToString()
        {
            return "Summary: 3\r\n" + $"Name: {FirstName}\r\n" + $"Last Name: {LastName}\r\n" +
                   $"Description: {Description}";
        }

    }
}
