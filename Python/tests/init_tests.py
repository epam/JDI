import unittest

from JDI.web.selenium.settings.WebSettings import WebSettings
from tests.site.pageobjects.epam_jdi_site import EpamJDISite
from JDI.web.selenium.elements.composite.web_site import WebSite
from tests.site.entities.user import User


class InitTests(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        WebSite.init(EpamJDISite, WebSettings.use_driver("chrome"))
        WebSettings.logger.info("Run Tests")
        EpamJDISite.home_page.open()
        EpamJDISite.login_page.submit(User.default())
        # WebSettings.get_logger().info("Run Tests")

    @classmethod
    def tearDownClass(cls):
        WebSettings.quit_browser()
