using System.Collections.Generic;
using JDI_Tests.Entities;

namespace JDI_Tests.Epam_UIObjects.DataProviders
{
    public class AttendeesProvider
    {
        public static IEnumerable<Attendee> Attendees
        {
            get
            {
                yield return new Attendee
                {
                    City = "Saint-Petersburg",
                    Comment = "I WANT TO WORK IN EPAM!!!",
                    Country = "Russian Federation",
                    Cv = "my_cv.pdf",
                    Email = "roman_iovlev@epam.com",
                    Filter = new JobSearchFilter(),
                    LastName = "Iovlev",
                    Name = "Roman"
                };
                yield return new Attendee
                {
                    City = "Mumbai",
                    Comment = "I also want to work in Epam",
                    Country = "India",
                    Cv = "new_document.txt",
                    Email = "Kamal_Haasan@example.com",
                    Filter = new JobSearchFilter(),
                    LastName = "Haasan",
                    Name = "Kamal"
                };
            }
        }
    }
}
