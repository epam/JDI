using System.Collections.Generic;
using JDI_UIWebTests.DataProviders;
using JDI_UIWebTests.Tests.Complex.Table.Base;
using NUnit.Framework;

namespace JDI_UIWebTests.Tests.Complex.Table
{
    [TestFixture]
    class NegativeTableTests : SupportTableTestBase
    {
        [Test]
        [TestCaseSource(typeof(IndexesProvider), nameof(IndexesProvider.Indexes))]
        public void IllegalRowIndexTest(int rowIndex)
        {
            // ExpectedException attribute no longer supported in NUnit 3
            Assert.Throws<AssertionException>(() => Table.Row(rowIndex));
        }

        [Test]
        [TestCaseSource(typeof (IndexesProvider), nameof(IndexesProvider.Indexes))]
        public void IllegalColumnIndexTest(int columnIndex)
        {
            Assert.Throws<AssertionException>(() => Table.Column(columnIndex));
        }

        [Test]
        public void IllegalHeaderNameTest()
        {
            Assert.Throws<KeyNotFoundException>(() => Table.Header("Column_illegal").GetWebElement());
        }

        [Test]
        public void IllegalHeaderIndexTest()
        {
            Assert.Throws<KeyNotFoundException>(() => Table.Rows.Header("Row_illegal").GetWebElement());
        }
    }
}
