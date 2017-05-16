import unittest

from JDI.web.selenium.driver.selenium_driver_factory import SeleniumDriverFactory
from JDI.web.selenium.elements.api_interact.get_element_module import GetElementModule
from JDI.web.selenium.elements.api_interact.find_element_by import By


class SmokeTests(unittest.TestCase):
    def setUp(self):
        driver_factory = SeleniumDriverFactory()
        driver_factory.register_driver("chrome")
        driver_factory.get_driver().get("https://google.com")

    def test_get_page(self):
        gem = GetElementModule(By.id("lst-ib"))
        gem.get_element()



