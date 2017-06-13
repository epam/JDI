from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class ImageTextx(InitTests):
    ALT = "ALT"
    SRC = "https://jdi-framework.github.io/tests/images/Logo_Epam_Color.svg"

    clickable_item = EpamJDISite.home_page.logo_image

    def setUp(self):
        Preconditions.HOME_PAGE.is_in_state()

    def test_click(self):
        EpamJDISite.contact_form_page.open()
        self.clickable_item.click()
        EpamJDISite.home_page.check_opened()

    def test_set_attribute(self):
        attribute_name = "testAttr"
        value = "testValue"
        self.clickable_item.set_attribute(attribute_name, value)
        Assert.check_text(self.clickable_item.get_element().get_attribute(attribute_name), value)

    def test_get_source(self):
        Assert.check_text(self.clickable_item.get_source(), self.SRC)

    def test_get_tip(self):
        Assert.check_text(self.clickable_item.get_alt(), self.ALT)