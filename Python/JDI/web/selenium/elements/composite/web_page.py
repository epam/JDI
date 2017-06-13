from JDI.core.interfaces.check_page_types import CheckPageTypes
from JDI.core.settings.jdi_settings import JDISettings
from JDI.jdi_assert.testing.assertion import Assert
from JDI.web.selenium.elements.base.base_element import BaseElement


class WebPage(BaseElement):

    checkUrlType = CheckPageTypes.EQUAL
    checkTitleType = CheckPageTypes.EQUAL

    url = None
    title = None

    def __init__(self, url, title):
        self.url = JDISettings.get_domain() + url
        self.title = title
        super(WebPage, self).__init__()

    def open(self):
        self.get_driver().get(self.url)

    def check_opened(self):
        JDISettings.asserter._is_true(self.verify_opened(), "Page '{0}' is not opened".format(self.__str__()))

    def verify_opened(self):
        result = False
        if self.checkUrlType == CheckPageTypes.EQUAL:
            result = self.check_url()
        elif self.checkUrlType == CheckPageTypes.MATCH:
            result = self.match_url()
        elif self.checkUrlType == CheckPageTypes.CONTAINS:
            result = self.contains_url()
        if not result:
            return False

        if self.checkTitleType == CheckPageTypes.EQUAL:
            return self.check_title()
        if self.checkTitleType == CheckPageTypes.MATCH:
            return self.match_title()
        if self.checkTitleType == CheckPageTypes.CONTAINS:
            return self.contains_title()

        return False


    def check_url(self):
        return JDISettings.get_driver_factory().get_driver().current_url == self.url

    def check_title(self):
        return JDISettings.get_driver_factory().get_driver().title == self.title

    def match_url(self):
        return JDISettings.get_driver_factory().get_driver().current_url.search(self.url)

    def match_title(self):
        return JDISettings.get_driver_factory().get_driver().current_title.search(self.title)

    def contains_url(self):
        return JDISettings.get_driver_factory().get_driver().current_url in self.url

    def contains_title(self):
        return JDISettings.get_driver_factory().get_driver().current_title in self.title

