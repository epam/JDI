from JDI.core.interfaces.application import Application
from JDI.core.settings.jdi_settings import JDISettings
from JDI.web.selenium.elements.web_cascade_init import WebCascadeInit
from JDI.web.selenium.settings.WebSettings import WebSettings


class WebSite(Application):

    @staticmethod
    def _init(site):
        WebCascadeInit().init_site_page(site)

    @staticmethod
    def _init(site, driver_name):
        WebSettings.use_driver(driver_name)
        WebCascadeInit().init_site_page(site)

    @staticmethod
    def init(site, driver_name, property_path):
        JDISettings.JDI_SETTINGS_FILE_PATH = property_path
        WebSite._init(site, driver_name)
