from JDI.core.settings.jdi_settings import JDISettings
from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class TextFieldTests(InitTests):

    text_field = EpamJDISite.contact_form_page.name_text_field
    input_text = "text123!@#$%^&*()"
    initial_text = "init"

    def setUp(self):
        Preconditions.CONTACT_PAGE.is_in_state()
        self.text_field.input(self.initial_text)

    def test_input(self):
        self.text_field.input(self.input_text)
        Assert.assert_equal(self.text_field.get_text(), self.initial_text+self.input_text)

    def test_send_key(self):
        self.text_field.send_keys(self.input_text)
        Assert.assert_equal(self.text_field.get_text(), self.initial_text+self.input_text)

    def test_new_input(self):
        self.text_field.new_input(self.input_text)
        Assert.assert_equal(self.text_field.get_text(), self.input_text)

    def test_clear(self):
        self.text_field.clear()
        Assert.assert_equal(self.text_field.get_text(), "")

    def test_multi_key(self):
        for letter in self.input_text:
            self.text_field.send_keys(letter)
        Assert.assert_equal(self.text_field.get_text(), self.initial_text+self.input_text)

    def test_focus(self):
        value = "value"
        attribute_name = "focusTest"
        self.text_field.set_attribute(attribute_name, value)
        self.text_field.focus()
        driver = JDISettings.get_driver_factory().get_driver()
        result_value = driver._switch_to.active_element.get_attribute(attribute_name)
        Assert.assert_equal(value, result_value)
