from JDI.core.settings.jdi_settings import JDISettings
from JDI.jdi_assert.testing.assertion import Assert
from JDI.web.selenium.elements.complex.common_action_data import CommonActionData
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class CheckBoxText(InitTests):

    check_box = EpamJDISite.metals_colors_page.cb_water

    def setUp(self):
        Preconditions.METALS_AND_COLORS_PAGE.is_in_state()

    def tearDown(self):
        JDISettings.get_driver_factory().get_driver().refresh()

    # @staticmethod
    # @pytest.mark.parametrize('input', 'expected', [(1, 2)])
    # # @ddt.data(("True", True), ("1", True), ("False", False), ("0", False))
    # def test_set_value(input, expected):
    #     if not expected:
    #         EpamJDISite.metals_colors_page.cb_water.click()
    #     EpamJDISite.metals_colors_page.cb_water.set_value(input)
    #     CommonActionData.check_action("Water: condition changed to " + expected.lower())

    def test_check_single(self):
        self.check_box.check()
        CommonActionData.check_action("Water: condition changed to true")

    def test_uncheck_single(self):
        self.check_box.click()
        self.check_box.uncheck()
        CommonActionData.check_action("Water: condition changed to false")

    def test_is_check(self):
        Assert.is_false(self.check_box.is_checked())
        self.check_box.click()
        Assert.is_true(self.check_box.is_checked())

    def test_multi_uncheck(self):
        self.check_box.click()
        self.check_box.uncheck()
        self.check_box.uncheck()
        CommonActionData.check_action("Water: condition changed to false")

    def test_multi_check(self):
        self.check_box.check()
        self.check_box.check()
        CommonActionData.check_action("Water: condition changed to true")

    def test_click(self):
        self.check_box.click()
        CommonActionData.check_action("Water: condition changed to true")
        self.check_box.click()
        CommonActionData.check_action("Water: condition changed to false")


   # @data("1")
    #@parameterized(1)
   # def test_set_value(self, input):
   #     self.check_box.click()
   #     self.check_box.set_value(input)
    #    CommonActionData.check_action("Water: condition changed to ")



