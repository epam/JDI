from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.entities import Odds
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.main.utils.common_action_data import CommonActionsData
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class SelectorTests(InitTests):
    odd_options = ["1", "3", "5", "7"]

    radio_buttons = EpamJDISite.metals_colors_page.summary.odds_selector

    def setUp(self):
        super(SelectorTests, self).setUp(self.id().split(".")[-1])
        Preconditions.METALS_AND_COLORS_PAGE.is_in_state()

    def test_select_string(self):
        self.radio_buttons.select("7")
        CommonActionsData.check_action("Summary (Odd): value changed to 7")

    def test_select_index(self):
        self.radio_buttons.select(4)
        CommonActionsData.check_action("Summary (Odd): value changed to 7")

    def test_select_enum(self):
        self.radio_buttons.select(Odds.SEVEN)
        CommonActionsData.check_action("Summary (Odd): value changed to 7")

    def test_get_options(self):
        Assert.assert_equal(self.radio_buttons.get_options(), self.odd_options)

    def test_get_names(self):
        Assert.assert_equal(self.radio_buttons.get_names(), self.odd_options)

    def test_get_values(self):
        Assert.assert_equal(self.radio_buttons.get_values(), self.odd_options)

    def test_get_options_as_text(self):
        Assert.assert_equal(self.radio_buttons.get_options_as_text(), ", ".join(self.odd_options))

    def test_set_value_text(self):
        self.radio_buttons.set_value("7")
        CommonActionsData.check_action("Summary (Odd): value changed to 7")

    def test_get_name(self):
        Assert.assert_equal(self.radio_buttons.get_name(),"odds_selector")

    def test_get_selected(self):
        Assert.assert_equal(self.radio_buttons.get_selected(), "1")

    def test_get_selected_index(self):
        Assert.assert_equal(self.radio_buttons.get_selected_index(), 0)

    def test_is_selected(self):
        Assert.assert_equal(self.radio_buttons.is_selected("7"), False)
        Assert.assert_equal(self.radio_buttons.is_selected("1"), True)

    def test_is_selected_enum(self):
        Assert.assert_equal(self.radio_buttons.is_selected(Odds.SEVEN), False)
        Assert.assert_equal(self.radio_buttons.is_selected(Odds.ONE), True)

    def test_get_value(self):
        Assert.assert_equal(self.radio_buttons.get_value(), "1")