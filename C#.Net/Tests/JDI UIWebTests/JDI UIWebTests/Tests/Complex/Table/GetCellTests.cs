using JDI_UIWebTests.Tests.Complex.Table.Base;
using JDI_Web.Selenium.Elements.Complex.Table;
using NUnit.Framework;
using Assert = JDI_Matchers.NUnit.Assert;

namespace JDI_UIWebTests.Tests.Complex.Table
{
    [TestFixture]
    class GetCellTests : SupportTableTestBase
    {
        private readonly string _cellValue = "Log4J, TestNG log, Custom";

        [Test]
        public void GetCellIntIntTest()
        {
            Assert.AreEquals(Table.Cell(2, 4).GetText, _cellValue);
        }

        [Test]
        public void GetCellStringStringTest()
        {
            Assert.AreEquals(Table.Cell("Now", "4").GetText, _cellValue);
        }

        [Test]
        public void GetCellParamsStringStringTest()
        {
            Assert.AreEquals(Table.Cell(Column.column("Now"), Row.row("4")).GetText, _cellValue);
        }

        [Test]
        public void GetCellParamsIntStringTest()
        {
            Assert.AreEquals(Table.Cell(Column.column(2), Row.row("4")).GetText, _cellValue);
        }

        [Test]
        public void GetCellParamsStringIntTest()
        {
            Assert.AreEquals(Table.Cell(Column.column("Now"), Row.row(4)).GetText, _cellValue);
        }

        [Test]
        public void GetCellParamsIntIntTest()
        {
            Assert.AreEquals(Table.Cell(Column.column(2), Row.row(4)).GetText, _cellValue);
        }
    }
}
