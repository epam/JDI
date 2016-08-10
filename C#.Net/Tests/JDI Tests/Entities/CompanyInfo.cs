using JDI_Commons;

namespace JDI_Tests.Entities
{
    public class CompanyInfo
    {
        public string Company;
        public string Contact;
        public string Country;

        public override string ToString()
        {
            return new[] {Company, Contact, Country}.Print();
        }
        public bool Equals(CompanyInfo obj)
        {
            return obj.Company.Equals(Company) &&
                    obj.Contact.Equals(Contact) &&
                    obj.Country.Equals(Country);
        }
    }
}
