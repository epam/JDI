from JDI.core.settings.jdi_settings import JDISettings
from JDI.web.selenium.elements.base.base_element import BaseElement


class WebPage(BaseElement):

    url = None
    title = None

    def __init__(self, url, title):
        self.url = JDISettings.get_domain() + url
        self.title = title
        super(WebPage, self).__init__()

    def open(self):
        self.get_driver().get(self.url)


