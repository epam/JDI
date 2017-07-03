import os
from JDI.core.settings.jdi_settings import JDISettings

class WebDriverProvider(object):

    @staticmethod
    def get_chrome_driver_path():
        return JDISettings.get_driver_path() + "/chromedriver.exe"
