using System.Collections.Generic;
using System.Linq;
using JDI_Commons;
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
            CheckHeaders(headers);
            var companies = W3CSite.TablePage.Companies.Entities();
            Assert.AreEqual(companies.Select(c => c.ToString()).Print(";"), 
                "Alfreds Futterkiste, Maria Anders, Germany;" +
                "Centro comercial Moctezuma, Francisco Chang, Mexico;" +
                "Ernst Handel, Roland Mendel, Austria;" +
                "Island Trading, Helen Bennett, UK;" +
                "Laughing Bacchus Winecellars, Yoshi Tannamuri, Canada;" +
                "Magazzini Alimentari Riuniti, Giovanni Rovelli, Italy"
);

        }

        private void CheckHeaders(IList<string> headers)
        {
            var expectedHeaders = new List<string> {"Company", "Contact", "Country"};
            for (var i = 0; i < headers.Count; i++)
                Assert.AreEqual(headers[i], expectedHeaders[i]);
        }
    }
}
