namespace Epam.Tests.Scenarios.Entities
{
    public class Attendee
    {
        public JobSearchFilter Filter = new JobSearchFilter();

        public string Name = "Roman";
        public string LastName = "Iovlev";
        public string Email = "roman_iovlev@epam.com";
        public string Country;
        public string City = "Saint-Petersburg";
        public string Cv;// = "jdi-uitest-tutorialtests\\src\\test\\resources\\cv.txt";
        public string Comment = "I WANT TO WORK IN EPAM!!!";
        
        public new string ToString()
        {
            return Name + " " + LastName;
        }
    }
}
