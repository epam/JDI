import unittest

from JDI.web.selenium.settings.WebSettings import WebSettings
from JDI.web.selenium.elements.composite.web_site import WebSite
from Test.jdi_uitests_webtests.main.entities.user import User
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite


class InitTests(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        WebSite.init(EpamJDISite, "chrome")
        WebSettings.logger.info("Run Tests")
        EpamJDISite.home_page.open()
        EpamJDISite.login_page.submit(User.default())

    @classmethod
    def tearDownClass(cls):
        WebSettings.quit_browser()
