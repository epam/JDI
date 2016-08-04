using JDI_Tests.Enums;
using OpenQA.Selenium;

namespace JDI_Tests.Entities
{
    public class JobSearchFilter
    {
        public string Keywords = "QA" + Keys.Return;
        public string Category = "Software Test Engineering";
        public string Location = Locations.SaintPetersburg;
    }
}
