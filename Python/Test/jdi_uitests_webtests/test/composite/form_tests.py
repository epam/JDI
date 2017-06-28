from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.entities.contact import Contact
from Test.jdi_uitests_webtests.main.enums.entities import Buttons
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.main.utils.common_action_data import CommonActionsData
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class FormTests(InitTests):
    form = EpamJDISite.contact_form_page.contact_form
    contact = Contact("Ivan", "Ivanov", "Smart Man")

    def setUp(self):
        Preconditions.CONTACT_PAGE.is_in_state()

    def test_fill(self):
        self.form.fill(self.contact)
        Assert.assert_equal(self.form.get_form_value(), {"Ivan", "Ivanov", "Smart Man"})

    def test_submit_form(self):
        self.form.submit_form(self.contact)
        Assert.assert_equal(EpamJDISite.contact_form_page.result.get_text(), str(self.contact))

    def test_submit_spec_button_string(self):
        self.form.submit_form(self.contact, button_name="submit")
        Assert.assert_equal(EpamJDISite.contact_form_page.result.get_text(), str(self.contact))

    def test_submit_speck_button_enum(self):
        self.form.submit_form(self.contact, Buttons.SUBMIT)
        Assert.assert_equal(EpamJDISite.contact_form_page.result.get_text(), str(self.contact))

    def test_submit_string(self):
        self.form.submit_form(self.contact.description)
        Assert.assert_equal(EpamJDISite.contact_form_page.result.get_text(), "Summary: 3\nDescription: {0}".format(self.contact.description))

    def test_verify(self):
        self.form.fill(self.contact)
        Assert.is_true(self.form.verify(self.contact))

    def test_check(self):
        self.form.fill(self.contact)
        self.form.check(self.contact)

