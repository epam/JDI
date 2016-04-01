namespace Epam.Tests.TutorialTests.Career.PageObjects
{
    public class Attendee
    {
        public JobSearchFilter filter = new JobSearchFilter();

        public string name = "Roman";
        public string lastName = "Iovlev";
        public string email = "roman_iovlev@epam.com";
        public string country;
        public string city = "Saint-Petersburg";
        public string cv;// = "jdi-uitest-tutorialtests\\src\\test\\resources\\cv.txt";
        public string comment = "I WANT TO WORK IN EPAM!!!";

        public Attendee() { }
        public Attendee(string name)
        {
            this.name = name;
        }
        
        public override string ToString()
        {
            return name + " " + lastName;
        }

    }
}
