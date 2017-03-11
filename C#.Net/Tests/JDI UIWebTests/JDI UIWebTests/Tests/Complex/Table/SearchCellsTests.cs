using System.Collections.Generic;
using JDI_Matchers.NUnit;
using JDI_UIWebTests.Tests.Complex.Table.Base;
using JDI_Web.Selenium.Elements.Complex.Table;
using JDI_Web.Selenium.Elements.Complex.Table.Interfaces;
using NUnit.Framework;

namespace JDI_UIWebTests.Tests.Complex.Table
{
    [TestFixture]    
    public class SearchCellsTests : SupportTableTestBase
    {
        [Test]
        public void CellsEqualsTest()
        {
            CheckCells(Table.Cells("MSTest, NUnit, Epam"));
        }

        [Test]
        public void CellsMatchTest()
        {
            CheckCells(Table.CellsMatch(".*Test, NUnit, Epam"));
        }

        [Test]
        public void CellEqualsTest()
        {
            CheckCell(Table.Cell("MSTest, NUnit, Epam"));
        }

        [Test]
        public void CellMatchTest()
        {
            CheckCell(Table.CellMatch(".*Test, NUnit, Epam"));
        }

        [Test]
        public void CellInColumnNumEqualsTest()
        {
            CheckCell(Table.Cell("MSTest, NUnit, Epam", Column.column(3)));
        }

        [Test]
        public void CellInColumnNameEqualsTest()
        {
            CheckCell(Table.Cell("MSTest, NUnit, Epam", Column.column("Plans")));
        }

        [Test]
        public void CellInRowNumEqualsTest()
        {
            CheckCell(Table.Cell("MSTest, NUnit, Epam", Row.row(2)));
        }

        [Test]
        public void CellInRowNameEqualsTest()
        {
            CheckCell(Table.Cell("MSTest, NUnit, Epam", Row.row("2")));
        }

        [Test]
        public void CellsMatchInColumnNumEqualsTest()
        {
            CheckCells(Table.CellsMatch(".*Test, NUnit, Epam", Column.column(3)));
        }

        [Test]
        public void CellsMatchInColumnNameEqualsTest()
        {
            CheckCells(Table.CellsMatch(".*MSTest, NUnit, Epam", Column.column("Plans")));
        }

        [Test]
        public void CellsMatchInRowNumEqualsTest()
        {
            var cells = Table.CellsMatch(".*MSTest, NUnit, Epam", Row.row(2));
            new Check().AreEquals(cells.Count, 1);
            CheckCell(cells[0]);
        }

        [Test]
        public void CellsMatchInRowNameEqualsTest()
        {
            var cells = Table.CellsMatch(".*MSTest, NUnit, Epam", Row.row("2"));
            new Check().AreEquals(cells.Count, 1);
            CheckCell(cells[0]);
        }

        private void CheckCell(ICell cell)
        {
            new Check().AreEquals(
                $"Value: {cell.Value}; " +
                $"Text: {cell.GetText}; {cell.ColumnName}/{cell.RowName}; {cell.ColumnNum}/{cell.RowNum}",
                "Value: MSTest, NUnit, Epam; Text: MSTest, NUnit, Epam; Plans/2; 3/2");
        }

        private void CheckCells(IList<ICell> cells)
        { 
            new Check("Cells size").AreEquals(cells.Count, 2);

            new Check("Cell 1 coordinates").AreEquals(
                $"Value: {cells[0].Value}; " +
                $"{cells[0].ColumnName}/{cells[0].RowName}; " +
                $"{cells[0].ColumnNum}/{cells[0].RowNum}", "Value: MSTest, NUnit, Epam; Plans/2; 3/2");

            new Check("Cell 2 coordinates").AreEquals(
                $"Value: {cells[1].Value}; " +
                $"{cells[1].ColumnName}/{cells[1].RowName}; " +
                $"{cells[1].ColumnNum}/{cells[1].RowNum}", "Value: MSTest, NUnit, Epam; Plans/3; 3/3");
        }
    }
}
