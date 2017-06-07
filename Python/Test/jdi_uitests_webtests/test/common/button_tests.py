from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions

class ButtonTests(InitTests):

    button = EpamJDISite.metals_colors_page.calculateButton

    def setUp(self):
        Preconditions.METALS_AND_COLORS_PAGE.isInState()

    def test_click(self):
        self.button.click()
