using System.Collections.Generic;
using System.Linq;
using JDI_Commons;
using JDI_Web.Selenium.Elements.Composite;
using NUnit.Framework;

namespace JDI_Tests.Tests.W3C
{
    [TestFixture]
    public class TableTests 
    {
        [Test]
        public void TableTest()
        {
            WebSite.Init(typeof(W3CSite));
            W3CSite.TablePage.IsOpened();
            var headers = W3CSite.TablePage.Companies.Columns.AllHeaders;
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
        [Test]
        public void TableTestShortInfo()
        {
            WebSite.Init(typeof(W3CSite));
            W3CSite.TablePage.IsOpened();
            var headers = W3CSite.TablePage.CompaniesShort.Columns.AllHeaders;
            CheckHeaders(headers);
            var companies = W3CSite.TablePage.CompaniesShort.Entities();
            Assert.AreEqual(companies.Select(c => c.ToString()).Print(";"),
                "Alfreds Futterkiste, Germany;" +
                "Centro comercial Moctezuma, Mexico;" +
                "Ernst Handel, Austria;" +
                "Island Trading, UK;" +
                "Laughing Bacchus Winecellars, Canada;" +
                "Magazzini Alimentari Riuniti, Italy"
            );
        }

        private void CheckHeaders(IList<string> headers)
        {
            var expectedHeaders = new List<string> {"Company", "Contact", "Country"};
            headers.ForEach(header => Assert.IsTrue(expectedHeaders.Contains(header)));
        }
    }
}
