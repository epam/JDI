from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class SearchTests(InitTests):
    def setUp(self):
        Preconditions.HOME_PAGE.is_in_state()

    def test_fill(self):
        EpamJDISite.header.search_section.find("something")
        EpamJDISite.support_page.check_opened()
