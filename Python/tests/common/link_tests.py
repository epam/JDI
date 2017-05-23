from tests.init_tests import InitTests
from tests.site.pageobjects.epam_jdi_site import EpamJDISite
from tests.site.entities.user import User


class LinkTests(InitTests):

    @staticmethod
    def test_first():
        EpamJDISite.home_page.open()
        EpamJDISite.home_page.about.click()
        EpamJDISite.login_page.submit(User.default())


