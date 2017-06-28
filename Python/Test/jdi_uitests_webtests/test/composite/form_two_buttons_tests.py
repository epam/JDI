from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.entities.contact import Contact
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class FormTwoButtonsTests(InitTests):
    form = EpamJDISite.contact_form_page.contact_form_two_buttons
    contact = Contact("Ivan", "Ivanov", "Smart Man")

    def setUp(self):
        Preconditions.CONTACT_PAGE.is_in_state()

    def test_submit_spec_button_string(self):
        self.form.submit_form(self.contact, "calculate")
        Assert.assert_equal(EpamJDISite.contact_form_page.result.get_text(), "Summary: 3")