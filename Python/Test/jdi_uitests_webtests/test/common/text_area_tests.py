from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests
import time


class TextAreaTests(InitTests):
    LINES = ["line1", "line2", "line3"]
    textItem = EpamJDISite.contact_form_page.description

    def setUp(self):
        Preconditions.CONTACT_PAGE.is_in_state()

    def test_input_line(self):
        self.textItem.input_lines(self.LINES)
        lines = self.textItem.get_lines()
        Assert.assert_equal(lines, self.LINES)