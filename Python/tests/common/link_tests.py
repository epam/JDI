from tests.init_tests import InitTests
from tests.site.pageobjects.epam_jdi_site import EpamJDISite


class LinkTests(InitTests):

    @staticmethod
    def test_first():
        EpamJDISite.home_page.about.click()



