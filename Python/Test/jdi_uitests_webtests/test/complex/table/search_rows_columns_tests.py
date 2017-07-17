from JDI.jdi_assert.testing.assertion import Assert
from JDI.web.selenium.elements.complex.table.column import Column
from JDI.web.selenium.elements.complex.table.row import Row
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class SearchRowsColumnsTests(InitTests):
    expected_column = "1:Selenium, Custom " \
                      "2:TestNG, JUnit, Custom " \
                      "3:TestNG, JUnit, Custom " \
                      "4:Log4J, TestNG log, Custom " \
                      "5:Jenkins, Allure, Custom " \
                      "6:Custom "

    expected_row = "Type:Test Runner " \
                   "Now:TestNG, JUnit, Custom " \
                   "Plans:MSTest, NUnit, Epam "

    table = EpamJDISite.support_page.support_table

    def setUp(self):
        Preconditions.SUPPORT_PAGE.is_in_state()

    def test_column_by_num(self):
        actual_column = "".join(
            list(map(lambda x: "{0}:{1} ".format(str(x[0]), x[1].get_text()), self.table.column(2))))
        Assert.assert_equal(actual_column, self.expected_column)

    def test_column_by_name(self):
        actual_column = "".join(
            list(map(lambda x: "{0}:{1} ".format(str(x[0]), x[1].get_text()), self.table.column("Now"))))
        Assert.assert_equal(actual_column, self.expected_column)

    def test_row_by_num(self):
        actual_column = "".join(
            list(map(lambda x: "{0}:{1} ".format(str(x[0]), x[1].get_text()), self.table.row(2))))
        Assert.assert_equal(actual_column, self.expected_row)

    def test_row_by_name(self):
        actual_column = "".join(
            list(map(lambda x: "{0}:{1} ".format(str(x[0]), x[1].get_text()), self.table.row("2"))))
        Assert.assert_equal(actual_column, self.expected_row)

    def test_column_by_criteria_int(self):
        actual_column = "".join(
            list(map(lambda x: "{0}:{1} ".format(str(x[0]), x[1].get_text()),
                     self.table.column("TestNG, JUnit, Custom", Row.row(3)))))
        Assert.assert_equal(actual_column, self.expected_column)

    def test_column_by_criteria_name(self):
        actual_column = "".join(
            list(map(lambda x: "{0}:{1} ".format(str(x[0]), x[1].get_text()),
                     self.table.column("TestNG, JUnit, Custom", Row.row("3")))))
        Assert.assert_equal(actual_column, self.expected_column)

    def test_row_by_criteria_int(self):
        actual_column = "".join(
            list(map(lambda x: "{0}:{1} ".format(str(x[0]), x[1].get_text()),
                     self.table.row("MSTest, NUnit, Epam", Column.column(3)))))
        Assert.assert_equal(actual_column, self.expected_row)

    def test_row_by_criteria_string(self):
        actual_column = "".join(
            list(map(lambda x: "{0}:{1} ".format(str(x[0]), x[1].get_text()),
                     self.table.row("MSTest, NUnit, Epam", Column.column("Plans")))))
        Assert.assert_equal(actual_column, self.expected_row)

    # def test_rows_by_criteria(self):
    #     rows = self.table.get_rows("Plans=MSTest, NUnit, Epam")
    #     Assert.assert_equal(len(rows), 2)

