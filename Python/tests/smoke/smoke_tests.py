import unittest

from JDI.web.selenium.settings.WebSettings import WebSettings
from JDI.web.selenium.elements.composite.web_site import WebSite
from tests.site.yandex.yandex import YandexSite


class SmokeTests(unittest.TestCase):

    def setUp(self):
        WebSite.init(YandexSite, WebSettings.use_driver("chrome"))

    def test_first(self):
        YandexSite.home_page.open()
        YandexSite.home_page.yandex_link.click()
        self.assertEquals(YandexSite.home_page.get_driver().current_url, "https://yandex.ru/")

    def tearDown(self):
        WebSettings.quit_browser()

