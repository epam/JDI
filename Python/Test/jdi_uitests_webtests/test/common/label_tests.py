from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class LabelTests(InitTests):
    label = EpamJDISite.metals_colors_page.calculate_label

    def setUp(self):
        Preconditions.METALS_AND_COLORS_PAGE.is_in_state()

    def test_click(self):
        self.label.click()
        Assert.assert_element_test(self.label, "CALCULATE")
