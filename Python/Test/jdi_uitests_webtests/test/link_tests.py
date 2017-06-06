from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class LinkTests(InitTests):

    @staticmethod
    def test_first():
        EpamJDISite.home_page.about.click()



