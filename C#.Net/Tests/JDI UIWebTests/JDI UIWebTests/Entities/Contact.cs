using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JDI_UIWebTests.Entities
{
    public class Contact
    {
        public static readonly Contact DEFAULT_CONTACT = new Contact("Test", "Testov", "Test description", 3, 4);

        private string _firstName;
        private string _lastName;
        private string _description;
        private int _firstSummary;
        private int _secondSummary;

        public string FirstName { get { return  _firstName; } set { _firstName = value; } }
        public string LastName { get { return _lastName; } set { _lastName = value; } }
        public string Description { get { return _description; } set { _description = value; } }
        public int FirstSummary { get { return _firstSummary; } set { _firstSummary = value; } }
        public int SecondSummary { get { return _secondSummary; } set { _secondSummary = value; } }

        public Contact(string firstName, string lastName, string description, int firstSummary, int secondSummary)
        {
            _firstName = firstName;
            _lastName = lastName;
            _description = description;
            _firstSummary = firstSummary;
            _secondSummary = secondSummary;
        }

        public List<string> ToList()
        {
            return new List<string> { FirstName, LastName, Description};
        }

        public override string ToString() {
            return string.Format(
                    "Summary: 3\r\n"
                        + "Name: {0}\r\n"
                        + "Last Name: {1}\r\n"
                        + "Description: {2}",
                    FirstName, LastName, Description);
        }

    }
}
