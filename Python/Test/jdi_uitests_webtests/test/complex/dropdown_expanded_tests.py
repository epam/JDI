from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.entities import Colors
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.main.utils.common_action_data import CommonActionsData
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class DropdownExpandedTests(InitTests):

    odd_options = ["Colors", "Red", "Green", "Blue", "Yellow"]

    dropdown = EpamJDISite.metals_colors_page.color_dropdown

    def setUp(self):
        Preconditions.METALS_AND_COLORS_PAGE.is_in_state()
        self.dropdown.expand()

    def test_select_string(self):
        self.dropdown.select("Blue")
        CommonActionsData.check_action("Colors: value changed to Blue")

    def test_select_index(self):
        self.dropdown.select(4)
        CommonActionsData.check_action("Colors: value changed to Blue")

    def test_select_enum(self):
        self.dropdown.select(Colors.BLUE)
        CommonActionsData.check_action("Colors: value changed to Blue")

    def test_get_options(self):
        Assert.assert_equal(self.dropdown.get_options(), self.odd_options)

    def test_get_names(self):
        Assert.assert_equal(self.dropdown.get_names(), self.odd_options)

    def test_get_values(self):
        Assert.assert_equal(self.dropdown.get_values(), self.odd_options)

    def test_get_options_as_text(self):
        Assert.assert_equal(self.dropdown.get_options_as_text(), "Colors, Red, Green, Blue, Yellow")

    def test_set_value(self):
        self.dropdown.set_value("Blue")
        CommonActionsData.check_action("Colors: value changed to Blue")

    def test_get_name(self):
        Assert.assert_equal(self.dropdown.get_name(), "color_dropdown")

    def test_get_selected(self):
        Assert.assert_equal(self.dropdown.get_selected(), "Colors")

    def test_get_selected_index(self):
        CommonActionsData.check_action_throw_error(lambda: self.dropdown.get_selected_index(), CommonActionsData.no_elements_message())

    def test_is_selected(self):
        Assert.assert_equal(self.dropdown.is_selected("Colors"), True)

    def test_is_selected_enum(self):
        Assert.assert_equal(self.dropdown.is_selected(Colors.COLORS), True)

    def test_get_value(self):
        Assert.assert_equal(self.dropdown.get_value(), "Colors")