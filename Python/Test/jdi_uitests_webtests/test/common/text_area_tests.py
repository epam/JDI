from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class TextAreaTests(InitTests):
    LINES = ["line1", "line2", "line3"]
    text_item = EpamJDISite.contact_form_page.description

    def setUp(self):
        Preconditions.CONTACT_PAGE.is_in_state()

    def test_input_line(self):
        self.text_item.input_lines(self.LINES)
        lines = self.text_item.get_lines()
        Assert.assert_equal(lines, self.LINES)

    def test_add_new_line(self):
        new_line = "newLine"
        self.text_item.send_keys("garbageString")
        self.text_item.add_new_line(new_line)
        lines = self.text_item.get_lines()
        Assert.assert_equal(new_line, lines[1])

    def test_get_lines(self):
        self.text_item.send_keys(self.LINES[0] + "\n" + self.LINES[1] + "\n" + self.LINES[2])
        Assert.assert_equal(self.text_item.get_lines(), self.LINES)


