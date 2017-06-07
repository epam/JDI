from JDI.web.selenium.preconditions.web_preconditions import WebPreconditions
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class LinkTests(InitTests):

    def setUp(self):
        Preconditions.HOME_PAGE.isInState()

    @staticmethod
    def test_first():
        EpamJDISite.home_page.about.click()
        print(WebPreconditions.check_url("kk"))



