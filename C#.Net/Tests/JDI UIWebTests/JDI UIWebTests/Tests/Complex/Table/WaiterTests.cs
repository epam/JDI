using JDI_Matchers.NUnit;
using JDI_UIWebTests.Tests.Complex.Table.Base;
using JDI_Web.Selenium.Elements.Complex.Table;
using NUnit.Framework;
using static JDI_UIWebTests.Tests.Complex.CommonActionsData;
using static JDI_UIWebTests.UIObjects.TestSite;
using Assert = JDI_Matchers.NUnit.Assert;

namespace JDI_UIWebTests.Tests.Complex.Table
{
    [TestFixture]
    class WaiterTests : SupportTableTestBase
    {
        [Test]
        public void WaitExpectedRowsValueTest()
        {
            new Check("Find value").IsTrue(Table.WaitValue("Cucumber, Jbehave, Thucydides, SpecFlow", Row.row(6)));
        }

        [Test]
        public void WaitUnexpectedRowsValueTest()
        {
            new Check("Do not find value").IsFalse(Table.WaitValue("Cucumber, Jbehave, Thucydides, SpecFlow Unexepected", Row.row(6)));
        }

        [Test]
        public void WaitExpectedColumnsValueTest()
        {
            new Check("Find value").IsTrue(Table.WaitValue("Custom", Column.column(2)));
        }

        [Test]
        public void WaitUnexpectedColumnsValueTest()
        {
            new Check("Do not find value").IsFalse(Table.WaitValue("Custom Unexepected", Column.column(2)));
        }

        [Test]
        public void TableIsEmptyTest()
        {
            new Check("Table not empty").IsFalse(Table.Empty);
        }

        [Test]
        public void CellWaitTextTest()
        {
            HomePage.IsOpened();
            RunParallel(() => SupportPage.IsOpened());
            CheckText(() => Table.Cell(2, 2).WaitText("TestNG, JUnit, Custom"), "TestNG, JUnit, Custom");
        }

        [Test]
        public void CellWaitMatchTextTest()
        {
            HomePage.IsOpened();
            RunParallel(() => SupportPage.IsOpened());
            CheckText(() => Table.Cell(2, 2).WaitMatchText("[a-zA-Z, ]*JUnit,[a-zA-Z ]*"), "TestNG, JUnit, Custom");
        }

        [Test]
        public void WaitHaveRowsTest()
        {
            HomePage.IsOpened();
            RunParallel(() => SupportPage.IsOpened());
            Assert.IsTrue(Table.WaitHaveRows());
        }

        [Test]
        public void WaitRowsTest()
        {
            HomePage.IsOpened();
            RunParallel(() => SupportPage.IsOpened());
            Assert.IsTrue(Table.WaitRows(6));
        }

        [Test]
        public void WaitRowsTimeoutTest()
        {
            HomePage.IsOpened();
            RunParallel(() => SupportPage.IsOpened());
            Assert.IsFalse(Table.WaitRows(7));
        }
    }
}
