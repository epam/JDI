using JDI_Tests.Scenarios.Entities;
using JDI_Tests.Scenarios.Enums;
using JDI_Tests.Scenarios.Page_Objects;
using NUnit.Framework;

namespace JDI_Tests.Scenarios.Tests
{
    [TestFixture]
    public class TableTests 
    {
        [Test]
        public void TableTest()
        {
            W3CSite.TablePage.Open();
            var headers = W3CSite.TablePage.Companies.Headers;
            var companies = W3CSite.TablePage.Companies.Entities();
        }
        
    }
}
