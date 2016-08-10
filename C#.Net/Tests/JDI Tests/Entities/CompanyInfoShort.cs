using JDI_Commons;

namespace JDI_Tests.Entities
{
    public class CompanyInfoShort
    {
        public string Company;
        public string Country;

        public override string ToString()
        {
            return new[] {Company, Country}.Print();
        }
        public bool Equals(CompanyInfo obj)
        {
            return obj.Company.Equals(Company) &&
                    obj.Country.Equals(Country);
        }
    }
}
