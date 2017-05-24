from JDI.core.settings.jdi_settings import JDISettings
from JDI.web.selenium.driver.selenium_driver_factory import SeleniumDriverFactory
from JDI.core.logger.jdi_logger import JDILogger


class WebSettings(JDISettings):

    logger = JDILogger()
    domain = None

    @staticmethod
    def get_driver_factory():
        return JDISettings.get_driver_factory()

    @staticmethod
    def set_driver_factory(driver_factory):
        JDISettings._driver_factory = driver_factory

    @staticmethod
    def use_driver(driver_name):
        driver_factory = SeleniumDriverFactory()
        WebSettings.set_driver_factory(driver_factory)
        return driver_factory.register_driver(driver_name)

    @staticmethod
    def quit_browser():
        WebSettings.get_driver_factory()\
            .get_driver().quit()

