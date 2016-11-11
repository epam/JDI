using System.Linq;
using JDI_Commons;
using JDI_Matchers.NUnit;
using JDI_UIWebTests.Tests.Complex.Table.Base;
using JDI_Web.Selenium.Elements.Complex.Table;
using NUnit.Framework;

namespace JDI_UIWebTests.Tests.Complex.Table
{
    [TestFixture]
    public class SearchRowsColumnsTests : SupportTableTestBase
    {
        private static readonly string _expectedColumn2 =
            "1:Selenium, Custom, " +
            "2:TestNG, JUnit, Custom, " +
            "3:TestNG, JUnit, Custom, " +
            "4:Log4J, TestNG log, Custom, " +
            "5:Jenkins, Allure, Custom, " +
            "6:Custom";

        private static readonly string _expectedRow2 =
            "Type:Test Runner, " +
            "Now:TestNG, JUnit, Custom, " +
            "Plans:MSTest, NUnit, Epam";

        [Test]
        public void ColumnByNumTest()
        {
            var column = Table.Column(2).Select(pair => $"{pair.Key}:{pair.Value.GetText}");
            new Check().AreEquals(column.Print(), _expectedColumn2);
        }

        [Test]
        public void ColumnByNameTest()
        {
            var column = Table.Column("Now").Select(pair => $"{pair.Key}:{pair.Value.GetText}");
            new Check().AreEquals(column.Print(), _expectedColumn2);
        }

        [Test]
        public void RowByNumTest()
        {
            var row = Table.Row(2).Select(pair => $"{pair.Key}:{pair.Value.GetText}");
            new Check().AreEquals(row.Print(), _expectedRow2);
        }

        [Test]
        public void RowByNameTest()
        {
            var row = Table.Row("2").Select(pair => $"{pair.Key}:{pair.Value.GetText}");
            new Check().AreEquals(row.Print(), _expectedRow2);
        }

        [Test]
        public void ColumnByCriteriaIntTest()
        {
            var column = Table.Column("TestNG, JUnit, Custom", Row.row(3)).Select(pair => $"{pair.Key}:{pair.Value.GetText}");
            new Check().AreEquals(column.Print(), _expectedColumn2);
        }

        [Test]
        public void ColumnByCriteriaStringTest()
        {
            var column = Table.Column("TestNG, JUnit, Custom", Row.row("3")).Select(pair => $"{pair.Key}:{pair.Value.GetText}");
            new Check().AreEquals(column.Print(), _expectedColumn2);
        }

        [Test]
        public void RowByCriteriaIntTest()
        {
            var row = Table.Row("MSTest, NUnit, Epam", Column.column(3)).Select(pair => $"{pair.Key}:{pair.Value.GetText}");
            new Check().AreEquals(row.Print(), _expectedRow2);
        }

        [Test]
        public void RowByCriteriaStringTest()
        {
            var row = Table.Row("MSTest, NUnit, Epam", Column.column("Plans")).Select(pair => $"{pair.Key}:{pair.Value.GetText}");
            new Check().AreEquals(row.Print(), _expectedRow2);
        }

        [Test]
        public void RowsByCriteriaTest()
        {
            var rows = Table.GetRows("Plans=MSTest, NUnit, Epam");
            new Check("Rows count").AreEquals(rows.Count, 2);
            string stringRow =
                rows.Select(
                    pair => $"{pair.Key}:[{pair.Value.Select(pair2 => $"{pair2.Key}:{pair2.Value.GetText}").Print()}]")
                    .Print();
            new Check("Rows content").AreEquals(
                stringRow,
                "2:[Type:Test Runner, Now:TestNG, JUnit, Custom, Plans:MSTest, NUnit, Epam], " +
                "3:[Type:Asserter, Now:TestNG, JUnit, Custom, Plans:MSTest, NUnit, Epam]");
        }

        [Test]
        public void RowsByTwoCriteriasTest()
        {
            var rows = Table.GetRows("Plans=MSTest, NUnit, Epam", "Type=Asserter");
            new Check("Rows count").AreEquals(rows.Count, 1);
            string stringRow =
                rows.Select(
                    pair => $"{pair.Key}:[{pair.Value.Select(pair2 => $"{pair2.Key}:{pair2.Value.GetText}").Print()}]")
                    .Print();
            new Check("Rows content").AreEquals(
                stringRow,
                "3:[Type:Asserter, Now:TestNG, JUnit, Custom, Plans:MSTest, NUnit, Epam]");
        }

        [Test]
        public void ColumnsByCriteriaTest()
        {
            var columns = Table.GetColumns("1=Selenium, Custom");
            new Check().AreEquals(columns.Count, 1);
            string stringColumns =
                columns.Select(
                    pair => $"{pair.Key}:[{pair.Value.Select(pair2 => $"{pair2.Key}:{pair2.Value.GetText}").Print()}]")
                    .Print();
            new Check("Columns content").AreEquals(
                stringColumns,
                "Now:[1:Selenium, Custom, " +
                "2:TestNG, JUnit, Custom, " +
                "3:TestNG, JUnit, Custom, " +
                "4:Log4J, TestNG log, Custom, " +
                "5:Jenkins, Allure, Custom, " +
                "6:Custom]");
        }

        [Test]
        public void ColumnsByTwoCriteriasTest()
        {
            var columns = Table.GetColumns("2=Test Runner", "4=Logger");
            new Check().AreEquals(columns.Count, 1);
            string stringColumns =
                columns.Select(
                    pair => $"{pair.Key}:[{pair.Value.Select(pair2 => $"{pair2.Key}:{pair2.Value.GetText}").Print()}]")
                    .Print();
            new Check("Columns content").AreEquals(
                stringColumns,
                "Type:[1:Drivers, " +
                "2:Test Runner, " +
                "3:Asserter, " +
                "4:Logger, " +
                "5:Reporter, " +
                "6:BDD/DSL]");
        }

        [Test]
        public void ColumnsGetTest()
        {
            var columns = Table.Columns.Get();
            new Check("Columns count").AreEquals(columns.Count, 3);
            string stringColumns = columns.Select(
                pair => $"{pair.Key}:[{pair.Value.Select(pair2 => $"{pair2.Key}:{pair2.Value.GetText}").Print()}]")
                .Print();
            new Check("Columns content").AreEquals(
                stringColumns,
                "Type:[1:Drivers, " +
                "2:Test Runner, " +
                "3:Asserter, " +
                "4:Logger, " +
                "5:Reporter, " +
                "6:BDD/DSL], " +
                "Now:[1:Selenium, Custom, " +
                "2:TestNG, JUnit, Custom, " +
                "3:TestNG, JUnit, Custom, " +
                "4:Log4J, TestNG log, Custom, " +
                "5:Jenkins, Allure, Custom, " +
                "6:Custom], " +
                "Plans:[1:JavaScript, Appium, WinAPI, Sikuli, " +
                "2:MSTest, NUnit, Epam, " +
                "3:MSTest, NUnit, Epam, " +
                "4:Epam, XML/Json logging, Hyper logging, " +
                "5:EPAM Report portal, Serenity, TimCity, Hudson, " +
                "6:Cucumber, Jbehave, Thucydides, SpecFlow]");
        }

        [Test]
        public void ColumnsAsTextTest()
        {
            var columns = Table.Columns.AsText;
            new Check("Columns count").AreEquals(columns.Count, 3);
            string stringColumns = columns.Select(
                pair => $"{pair.Key}:[{pair.Value.Select(pair2 => $"{pair2.Key}:{pair2.Value}").Print()}]")
                .Print();
            new Check("Columns content").AreEquals(
                stringColumns,
                "Type:[1:Drivers, " +
                "2:Test Runner, " +
                "3:Asserter, " +
                "4:Logger, " +
                "5:Reporter, " +
                "6:BDD/DSL], " +
                "Now:[1:Selenium, Custom, " +
                "2:TestNG, JUnit, Custom, " +
                "3:TestNG, JUnit, Custom, " +
                "4:Log4J, TestNG log, Custom, " +
                "5:Jenkins, Allure, Custom, " +
                "6:Custom], " +
                "Plans:[1:JavaScript, Appium, WinAPI, Sikuli, " +
                "2:MSTest, NUnit, Epam, " +
                "3:MSTest, NUnit, Epam, " +
                "4:Epam, XML/Json logging, Hyper logging, " +
                "5:EPAM Report portal, Serenity, TimCity, Hudson, " +
                "6:Cucumber, Jbehave, Thucydides, SpecFlow]");
        }

        [Test]
        public void ColumnsGetByNumTest()
        {
            var column = Table.Columns.GetColumn(2);
            new Check("Column content").AreEquals(
                column.Select(pair => $"{pair.Key}:{pair.Value.Value}").Print(),
                _expectedColumn2);
        }

        [Test]
        public void ColumnsGetByNameTest()
        {
            var column = Table.Columns.GetColumn("Now");
            new Check("Column content").AreEquals(
                column.Select(pair => $"{pair.Key}:{pair.Value.Value}").Print(),
                _expectedColumn2);
        }

        [Test]
        public void ColumnsGetByNumAsTextTest()
        {
            var column = Table.Columns.GetColumnAsText(2);
            new Check("Column content").AreEquals(
                column.Select(pair => $"{pair.Key}:{pair.Value}").Print(),
                _expectedColumn2);
        }

        [Test]
        public void ColumnsGetByNameAsTextTest()
        {
            var column = Table.Columns.GetColumnAsText("Now");
            new Check("Column content").AreEquals(
                column.Select(pair => $"{pair.Key}:{pair.Value}").Print(),
                _expectedColumn2);
        }

        [Test]
        public void RowsGetTest()
        {
            var rows = Table.Rows.Get();
            new Check("Rows count").AreEquals(rows.Count, 6);
            new Check("Rows content").AreEquals(
                rows.Select(
                    pair => $"{pair.Key}:[{pair.Value.Select(pair2 => $"{pair2.Key}:{pair2.Value.GetText}").Print()}]")
                    .Print(),
                "1:[Type:Drivers, " +
                "Now:Selenium, Custom, " +
                "Plans:JavaScript, Appium, WinAPI, Sikuli], " +
                "2:[Type:Test Runner, " +
                "Now:TestNG, JUnit, Custom, " +
                "Plans:MSTest, NUnit, Epam], " +
                "3:[Type:Asserter, " +
                "Now:TestNG, JUnit, Custom, " +
                "Plans:MSTest, NUnit, Epam], " +
                "4:[Type:Logger, " +
                "Now:Log4J, TestNG log, Custom, " +
                "Plans:Epam, XML/Json logging, Hyper logging], " +
                "5:[Type:Reporter, " +
                "Now:Jenkins, Allure, Custom, " +
                "Plans:EPAM Report portal, Serenity, TimCity, Hudson], " +
                "6:[Type:BDD/DSL, " +
                "Now:Custom, " +
                "Plans:Cucumber, Jbehave, Thucydides, SpecFlow]");
        }

        [Test]
        public void RowsGetAsTextTest()
        {
            var rows = Table.Rows.AsText;
            new Check("Rows count").AreEquals(rows.Count, 6);
            new Check("Rows content").AreEquals(
                rows.Select(
                    pair => $"{pair.Key}:[{pair.Value.Select(pair2 => $"{pair2.Key}:{pair2.Value}").Print()}]")
                    .Print(),
                "1:[Type:Drivers, " +
                "Now:Selenium, Custom, " +
                "Plans:JavaScript, Appium, WinAPI, Sikuli], " +
                "2:[Type:Test Runner, " +
                "Now:TestNG, JUnit, Custom, " +
                "Plans:MSTest, NUnit, Epam], " +
                "3:[Type:Asserter, " +
                "Now:TestNG, JUnit, Custom, " +
                "Plans:MSTest, NUnit, Epam], " +
                "4:[Type:Logger, " +
                "Now:Log4J, TestNG log, Custom, " +
                "Plans:Epam, XML/Json logging, Hyper logging], " +
                "5:[Type:Reporter, " +
                "Now:Jenkins, Allure, Custom, " +
                "Plans:EPAM Report portal, Serenity, TimCity, Hudson], " +
                "6:[Type:BDD/DSL, " +
                "Now:Custom, " +
                "Plans:Cucumber, Jbehave, Thucydides, SpecFlow]");
        }

        [Test]
        public void RowsGetByNumTest()
        {
            var row = Table.Rows.GetRow(3);
            new Check("Row content").AreEquals(
                row.Select(pair => $"{pair.Key}:{pair.Value.Value}").Print(),
                "Type:Asserter, Now:TestNG, JUnit, Custom, Plans:MSTest, NUnit, Epam");
        }

        [Test]
        public void RowsGetByNameTest()
        {
            var row = Table.Rows.GetRow("3");
            new Check("Row content").AreEquals(
                row.Select(pair => $"{pair.Key}:{pair.Value.Value}").Print(),
                "Type:Asserter, Now:TestNG, JUnit, Custom, Plans:MSTest, NUnit, Epam");
        }

        [Test]
        public void RowsGetByNumAsTextTest()
        {
            var row = Table.Rows.GetRowAsText(3);
            new Check("Row content").AreEquals(
                row.Select(pair => $"{pair.Key}:{pair.Value}").Print(),
                "Type:Asserter, Now:TestNG, JUnit, Custom, Plans:MSTest, NUnit, Epam");
        }

        [Test]
        public void RowsGetByNameAsTextTest()
        {
            var row = Table.Rows.GetRowAsText("3");
            new Check("Row content").AreEquals(
                row.Select(pair => $"{pair.Key}:{pair.Value}").Print(),
                "Type:Asserter, Now:TestNG, JUnit, Custom, Plans:MSTest, NUnit, Epam");
        }

        [Test]
        public void CellsToColumnTest()
        {
            var cells = Table.Columns.CellsToColumn(new[] { Table.Cell(1, 1), Table.Cell(2, 2) });
            new Check("Columns Header").CollectionEquals(new[] { "Type", "Now" }, cells.Select(pair => pair.Key));
            new Check("Cells values").CollectionEquals(new[] { "Drivers", "TestNG, JUnit, Custom" },
                cells.Select(pair => pair.Value.Value));
        }

        [Test]
        public void CellsToRowTest()
        {
            var rows = Table.Rows.CellsToRow(new[] { Table.Cell(1, 1), Table.Cell(2, 2) });
            new Check("Rows Indexes").CollectionEquals(new[] { "1", "2" }, rows.Select(pair => pair.Key));
            new Check("Cells values").CollectionEquals(new[] { "Drivers", "TestNG, JUnit, Custom" },
                rows.Select(pair => pair.Value.Value));
        }

        [Test]
        public void RowValueByNumTests()
        {
            new Check("Row value").CollectionEquals(Table.RowValue(2),
                new[] { "Test Runner", "TestNG, JUnit, Custom", "MSTest, NUnit, Epam" });
        }

        [Test]
        public void RowValueByNameTests()
        {
            new Check("Row value").CollectionEquals(Table.RowValue("2"),
                new[] { "Test Runner", "TestNG, JUnit, Custom", "MSTest, NUnit, Epam" });
        }

        [Test]
        public void ColumnValueByNumTests()
        {
            new Check("Column value").CollectionEquals(Table.ColumnValue(2), new[] { "Selenium, Custom", "TestNG, JUnit, Custom", "TestNG, JUnit, Custom", "Log4J, TestNG log, Custom", "Jenkins, Allure, Custom", "Custom" });
        }

        [Test]
        public void ColumnValueByNameTests()
        {
            new Check("Column value").CollectionEquals(Table.ColumnValue("Now"), new[] { "Selenium, Custom", "TestNG, JUnit, Custom", "TestNG, JUnit, Custom", "Log4J, TestNG log, Custom", "Jenkins, Allure, Custom", "Custom" });
        }
    }
}
