from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class TextTests(InitTests):

    text_item = EpamJDISite.home_page.text_item
    expected_text = ("Lorem ipsum dolor sit amet, consectetur adipisicing elit,"
                    + " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    + " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris"
                    + " nisi ut aliquip ex ea commodo consequat Duis aute irure dolor in"
                    + " reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.").upper()

    reg_ex = ".* IPSUM DOLOR SIT AMET.*"
    contains = "ENIM AD MINIM VENIAM, QUIS NOSTRUD"

    def setUp(self):
        Preconditions.HOME_PAGE.is_in_state()

    def test_get_text(self):
        Assert.assert_equal(self.text_item.get_text(), self.expected_text)

    def test_get_value(self):
        Assert.assert_equal(self.text_item.get_value(), self.expected_text)

    def test_wait_match_text(self):
        Assert.is_true(self.text_item.wait_match_text(self.reg_ex))

    def test_wait_text(self):
        Assert.is_true(self.text_item.wait_contain_text(self.contains))

    def test_set_attribute(self):
        attribute_name = "testAttr"
        value = "testValue"
        self.text_item.set_attribute(attribute_name, value)
        Assert.assert_equal(self.text_item.get_web_element().get_attribute(attribute_name), value)
