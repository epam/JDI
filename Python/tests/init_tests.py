import unittest

from JDI.web.selenium.settings.WebSettings import WebSettings
from tests.site.pageobjects.epam_jdi_site import EpamJDISite
from JDI.web.selenium.elements.composite.web_site import WebSite


class InitTests(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        WebSite.init(EpamJDISite, WebSettings.use_driver("chrome"))

    @classmethod
    def tearDownClass(cls):
        WebSettings.quit_browser()
