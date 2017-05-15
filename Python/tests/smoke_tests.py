import unittest

from JDI.web.selenium.driver.selenium_driver_factory import SeleniumDriverFactory


class SmokeTests(unittest.TestCase):
    def setUp(self):
        self.driver_factory = SeleniumDriverFactory()
        self.driver_factory.register_driver("chrome")

    def test_get_page(self):
        driver = self.driver_factory.get_driver()
        driver.get("https://google.com")

