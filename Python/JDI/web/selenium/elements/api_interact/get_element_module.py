from JDI.core.settings.jdi_settings import JDISettings
from JDI.web.selenium.driver.utils.web_driver_by_utils import WebDriverByUtils


class GetElementModule(object):
    FAILED_TO_FIND_ELEMENT_MESSAGE = "Can't find Element '{0}' during  seconds"
    FIND_TO_MUCH_ELEMENTS_MESSAGE = "Find %s elements instead of one for Element '%s' during %s seconds"

    def __init__(self, by_locator=None, element=None):
        self.by_locator = by_locator
        self.element = element
        self.web_element = None
        self.web_elements = []
        self.frame_locator = None

    def get_element(self):
        return self.web_element if self.web_element is not None else self.__get_element_action()

    def get_elements(self):
        result = self.__search_elements()
        if result is None:
            raise Exception("Can't get Web Elements")
        return result

    def __get_element_action(self):
        result = self.__get_one_or_more_elements()
        if len(result) == 0:
            raise Exception(GetElementModule.FAILED_TO_FIND_ELEMENT_MESSAGE.format(self.element))
        elif len(result) == 1:
            return result[0]
        elif len(result) > 1:
            result = [element for element in result if element.is_displayed()]
            return result[0]
        else:
            raise Exception(GetElementModule.FIND_TO_MUCH_ELEMENTS_MESSAGE % len(result), self.element)

    def __get_one_or_more_elements(self):
        return self.web_elements if self.web_elements else self.__search_elements()

    def __search_elements(self):
        search_context = self.get_driver() \
            if WebDriverByUtils.contains_root(self.by_locator) \
            else self.get_search_context(self.element.parent)

        locator = WebDriverByUtils.trim_root(self.by_locator) \
            if WebDriverByUtils.contains_root(self.by_locator) \
            else self.by_locator

        if search_context is None:
            search_context = self.get_driver()
        return search_context.find_elements(locator[0], locator[1])

    @staticmethod
    def get_driver():
        return JDISettings.get_driver_factory().get_driver()

    def get_search_context(self, element):
        driver = self.get_driver()
        if element is None:
            return driver
        from JDI.web.selenium.elements.composite.web_site import WebSite
        try:
            if isinstance(element, WebSite) or isinstance(element.get_parent(), WebSite):
                return driver
        except: pass
        try:
            if issubclass(element, WebSite) or issubclass(element.get_parent(), WebSite):
                return driver
        except: pass
        from JDI.web.selenium.elements.base.base_element import BaseElement
        from JDI.web.selenium.elements.base.element import Element
        if (element is None or type(element) is BaseElement) or \
                (element.get_parent() is None and element.avatar.frame_locator is None):
            return self.get_driver().switch_to.default_content()
        if type(element) is Element and element.avatar.has_web_element():
            return element.get_web_element
        locator = element.get_locator()
        search_context = self.get_driver().switch_to.default_content() \
            if WebDriverByUtils.contains_root(locator) \
            else self.get_search_context(element.get_parent())
        locator = WebDriverByUtils.trim_root(locator) \
            if WebDriverByUtils.contains_root(locator) \
            else locator

        frame = element.avatar.frame_locator
        if frame is not None:
            self.switch_to_last_opened_window()
            res = search_context.find_element(element.avatar.frame_locator[0], element.avatar.frame_locator[1])
            driver.switch_to_frame(res)
        return search_context.find_element(locator[0], locator[1]) if locator is not None else search_context

    def switch_to_last_opened_window(self):
        self.get_driver().switch_to_window(self.get_driver().window_handles[-1])

    def set_web_element(self, web_element):
        self.web_element = web_element

    def has_locator(self):
        return self.by_locator is not None

    def has_we_element(self):
        return self.web_element is not None
