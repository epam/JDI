from JDI.web.selenium.settings.web_settings import WebSettings
from re import match


class WebPreconditions:

    @staticmethod
    def check_url(url):
        return match(".*/" + str(url) + "(\\?.*)?", WebSettings.get_driver_factory().get_driver().current_url) != None

    @staticmethod
    def open_uri(uri):
        WebSettings.get_driver_factory().get_driver().get(uri)
