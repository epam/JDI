from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.entities import Metals
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.main.utils.common_action_data import CommonActionsData
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class ComboBoxTests(InitTests):

    add_options = ["Col", "Gold", "Silver", "Bronze", "Selen"]
    add_options_string = "Col, Gold, Silver, Bronze, Selen"

    combo_box = EpamJDISite.metals_colors_page.combo_box

    def setUp(self):
        Preconditions.METALS_AND_COLORS_PAGE.is_in_state()

    def test_select_string(self):
        self.combo_box.select("Gold")
        CommonActionsData.check_action("Metals: value changed to Gold")

    def test_select_index(self):
        self.combo_box.select(3)
        CommonActionsData.check_action("Metals: value changed to Silver")

    def test_select_enum(self):
        self.combo_box.select(Metals.GOLD)
        CommonActionsData.check_action("Metals: value changed to Gold")

    def test_get_options(self):
        Assert.assert_equal(self.combo_box.get_options(), self.add_options)

    def test_get_names(self):
        Assert.assert_equal(self.combo_box.get_names(), self.add_options)

    def test_get_values(self):
        Assert.assert_equal(self.combo_box.get_values(), self.add_options)

    def test_get_options_as_text(self):
        Assert.assert_equal(self.combo_box.get_options_as_text(), self.add_options_string)

    def test_set_value_text(self):
        self.combo_box.set_value("Blue")
        CommonActionsData.loose_focus()
        CommonActionsData.check_action("Metals: value changed to Blue")

    def test_get_selected(self):
        self.combo_box.select("Gold")
        Assert.assert_equal(self.combo_box.get_selected(), "Gold")

    def test_get_selected_index(self):
        CommonActionsData.check_action_throw_error(lambda: self.combo_box.get_selected_index(), CommonActionsData.no_elements_message())

    def test_is_selected(self):
        Assert.assert_equal(self.combo_box.is_selected("Col"), True)

    def test_is_selected_enum(self):
        Assert.assert_equal(self.combo_box.is_selected(Metals.COL), True)

    def test_get_value(self):
        Assert.assert_equal(self.combo_box.get_value(), "Col")