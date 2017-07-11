import unittest

from ddt import ddt, data, unpack

from JDI.core.settings.jdi_settings import JDISettings
from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.main.utils.common_action_data import CommonActionsData
from Test.jdi_uitests_webtests.test.init_tests import InitTests


@ddt
class CheckBoxText(InitTests):

    check_box = EpamJDISite.metals_colors_page.cb_water

    def setUp(self):
        Preconditions.METALS_AND_COLORS_PAGE.is_in_state()

    def tearDown(self):
        JDISettings.get_driver_factory().get_driver().refresh()

    @data(("True", True), ("1", True), ("False", False), ("0", False))
    @unpack
    def test_set_value(self, input, expected):
         if not expected:
             EpamJDISite.metals_colors_page.cb_water.click()
         EpamJDISite.metals_colors_page.cb_water.set_value(input)
         CommonActionsData.check_action("Water: condition changed to " + str(expected).lower())

    @data("true ","1 "," false", "0 ", " ", "123", " 1", " 0", "!@#$%^&*", "qwdewf", "1qwe", "1qwe", "true123", "123true", "false123", "123false", "o", "O", "tr ue")
    def test_set_value_nothing_changes(self, input):
        self.check_box.click()
        self.check_box.set_value(input)
        CommonActionsData.check_action("Water: condition changed to true")
        self.check_box.click()
        self.check_box.set_value(input)
        CommonActionsData.check_action("Water: condition changed to false")

    def test_check_single(self):
        self.check_box.check()
        CommonActionsData.check_action("Water: condition changed to true")

    def test_uncheck_single(self):
        self.check_box.click()
        self.check_box.uncheck()
        CommonActionsData.check_action("Water: condition changed to false")

    def test_is_check(self):
        Assert.is_false(self.check_box.is_checked())
        self.check_box.click()
        Assert.is_true(self.check_box.is_checked())

    def test_multi_uncheck(self):
        self.check_box.click()
        self.check_box.uncheck()
        self.check_box.uncheck()
        CommonActionsData.check_action("Water: condition changed to false")

    def test_multi_check(self):
        self.check_box.check()
        self.check_box.check()
        CommonActionsData.check_action("Water: condition changed to true")

    def test_click(self):
        self.check_box.click()
        CommonActionsData.check_action("Water: condition changed to true")
        self.check_box.click()
        CommonActionsData.check_action("Water: condition changed to false")


if __name__ == "__main__":
    unittest.main()