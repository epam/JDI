import os

from selenium.webdriver.chrome.webdriver import WebDriver as ChromeDriver

from JDI.web.selenium.driver.driver_types import DriverTypes
from JDI.web.selenium.driver.web_driver_provider import WebDriverProvider
from JDI.core.settings.jdi_settings import JDISettings


class SeleniumDriverFactory(object):

    def __init__(self):
        self.current_driver = None
        self.browser_size = None
        self.drivers_path = JDISettings.get_driver_path()

    def register_driver(self, driver_name):
        driver_name = driver_name.lower()
        if driver_name == DriverTypes.chrome.name:
            self.current_driver = self.register_chrome_driver()
        if driver_name == DriverTypes.firefox.name:
            self.current_driver = self.register_firefox_driver()
        return driver_name

    def register_chrome_driver(self):
        chrome_driver = WebDriverProvider.get_chrome_driver_path()
        os.environ["webdriver.chrome.driver"] = chrome_driver

        return self.__web_driver_settings(ChromeDriver(chrome_driver))

    def register_firefox_driver(self):
        raise NotImplementedError

    def __web_driver_settings(self, driver):
        if self.browser_size is None:
            driver.maximize_window()
        else:
            driver.set_window_size(self.browser_size)
        driver.implicitly_wait(JDISettings.get_current_timeout_sec())
        return driver

    def get_driver(self):
        if self.current_driver is not None:
            return self.current_driver
        else:
            self.register_driver(DriverTypes.chrome.name)
            return self.current_driver




