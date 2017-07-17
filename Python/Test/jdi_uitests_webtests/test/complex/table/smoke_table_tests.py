from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class SmokeTableTests(InitTests):
    table_as_text = "||X||Type|Now|Plans||\n" \
    +"||1||Drivers|Selenium, Custom|JavaScript, Appium, WinAPI, Sikuli||\n" \
    +"||2||Test Runner|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" \
    +"||3||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" \
    +"||4||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" \
    +"||5||Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" \
    +"||6||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||"

    table = EpamJDISite.support_page.support_table

    def setUp(self):
        Preconditions.SUPPORT_PAGE.is_in_state()

    def test_get_value(self):
        Assert.assert_equal(self.table.get_value(), self.table_as_text)

    def test_get_text(self):
        Assert.assert_equal(self.table.get_text(), self.table_as_text)

    def test_table_dimension(self):
        Assert.assert_equal(str(self.table.columns.get_count()) + "/" + str(self.table.rows.get_count()), "3/6")

    def test_table_column_headers(self):
        Assert.assert_equal(", ".join(self.table.columns.get_headers()), "Type, Now, Plans")

    def test_table_headers(self):
        Assert.assert_equal(", ".join(self.table.get_headers()), "Type, Now, Plans")

    def test_table_row_headers(self):
        Assert.assert_equal(", ".join(self.table.rows.get_headers()), "1, 2, 3, 4, 5, 6")

    def test_table_is_not_empty(self):
        Assert.assert_false(self.table.is_empty())