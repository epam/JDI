from JDI.core.interfaces.check_page_types import CheckPageTypes
from JDI.core.settings.jdi_settings import JDISettings
from JDI.core.utils.decorators import scenario
from JDI.jdi_assert.testing.assertion import Assert
from JDI.web.selenium.elements.base.base_element import BaseElement
from JDI.web.selenium.settings.web_settings import WebSettings


class WebPage(BaseElement):
    check_url_type = CheckPageTypes.EQUAL
    check_title_type = CheckPageTypes.EQUAL

    url = None
    url_template = None
    title = None
    domain = None

    def __init__(self, url, title=None, domain=None, url_template=None, url_check_type=None, title_check_type=None):
        self.url = (JDISettings.get_domain() if domain is None else domain) + url
        self.title = title
        self.url_template = url_template
        if url_check_type is not None:
            self.check_url_type = url_check_type
        if title_check_type is not None:
            self.check_title_type = title_check_type
        super(WebPage, self).__init__()

    @scenario(action_name="Add cookie")
    def add_cookie(self, cookie):
        JDISettings.get_driver_factory().get_driver().add_cookie(cookie)

    @scenario(action_name="Go back to previous page")
    def back(self):
        JDISettings.get_driver_factory().get_driver().back()

    def check_opened(self):
        JDISettings.asserter._is_true(self.verify_opened(), "Page '{0}' is not opened".format(self.__str__()))

    def check_title(self):
        return JDISettings.get_driver_factory().get_driver().title == self.title

    def check_url(self):
        return JDISettings.get_driver_factory().get_driver().current_url == self.url

    @scenario(action_name="Delete all cookies")
    def clear_cache(self):
        JDISettings.get_driver_factory().get_driver().delete_all_cookies()

    def contains_title(self):
        return JDISettings.get_driver_factory().get_driver().title in self.title

    def contains_url(self):
        return self.url_template in JDISettings.get_driver_factory().get_driver().current_url

    @scenario(action_name="Go forward to next page")
    def forward(self):
        JDISettings.get_driver_factory().get_driver().forward()

    def match_url(self):
        return JDISettings.get_driver_factory().get_driver().current_url.search(self.url)

    def match_title(self):
        return JDISettings.get_driver_factory().get_driver().current_title.search(self.title)

    @scenario(action_name="Refresh page '%s", values_list={"title"})
    def refresh(self):
        JDISettings.get_driver_factory().get_driver().refresh()

    @scenario(action_name="Open page '%s' by url %s", values_list={"title", "url"})
    def open(self):
        self.get_driver().get(self.url)

    def verify_opened(self):
        result = False
        if self.check_url_type == CheckPageTypes.EQUAL:
            result = self.check_url()
        elif self.check_url_type == CheckPageTypes.MATCH:
            result = self.match_url()
        elif self.check_url_type == CheckPageTypes.CONTAINS:
            result = self.contains_url()
        if not result:
            return False

        if self.title is None:
            return True

        if self.check_title_type == CheckPageTypes.EQUAL:
            return self.check_title()
        if self.check_title_type == CheckPageTypes.MATCH:
            return self.match_title()
        if self.check_title_type == CheckPageTypes.CONTAINS:
            return self.contains_title()

        return False

    def should_be_opened(self):
        try:
            WebSettings.logger.info("Page '{0}' should be opened".format(self.get_name()))
            if self.verify_opened(): return
            self.open()
            self.check_opened()
        except Exception as ex:
            msg = "Can't open page '{0}'. Reason: {1}".format(self.get_name(), str(ex))
            WebSettings.logger.info(msg)
            raise Exception(msg)

