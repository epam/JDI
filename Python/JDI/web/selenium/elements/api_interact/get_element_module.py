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

        # self.logger = JDISettings.get_logger()

    def get_element(self):
        # self.logger.debug("Get Web Element: " + str(self.element))
        #  element = self.web_element if self.web_element is not None else self.__get_element_action()

        # if self.web_element is None:
        self.web_element = self.__get_element_action()
        element = self.web_element
        # self.logger.debug("One Element found")
        return element

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
        if str(element).__contains__("Site") or str(element.get_parent()).__contains__("Site"):
            return driver
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
          #  driver.switch_to.default_content()
            self.switch_to_last_opened_window()
            res = search_context.find_element(element.avatar.frame_locator[0], element.avatar.frame_locator[1])
            driver.switch_to_frame(res)
        return search_context.find_element(locator[0], locator[1]) if locator is not None else search_context
        # from JDI.web.selenium.elements.composite.web_site import WebSite
        # try:
        #     #if issubclass(type(self.element.parent), WebSite) or issubclass(self.element.parent, WebSite):
        #     if issubclass(type(self.element), WebSite) or issubclass(self.element, WebSite):
        #         return None
        # except:
        #     pass
        # if self.element.parent is not None:
        #     if "avatar" in dir(self.element.parent):
        #         locator = b_element.avatar.by_locator
        #         if locator is None:
        #             return None
        #         driver = self.get_driver()
        #         if b_element.avatar.frame_locator is not None:
        #             self.switch_to_last_opened_window()
        #             res = driver.find_element(locator[0], locator[1])
        #             driver.switch_to_frame(b_element.avatar.frame_locator[1])
        #             return driver
        #         locator2 = b_element.get_locator()
        #     # search_context = self.get_driver() \
        #     #     if WebDriverByUtils.contains_root(locator) \
        #     #     else self.get_search_context(self.element.parent)
        #         locator2 = WebDriverByUtils.trim_root(self.by_locator) \
        #             if WebDriverByUtils.contains_root(self.by_locator[1]) \
        #             else self.by_locator
        #         return driver.find_element(locator[0], locator[1])

    def switch_to_last_opened_window(self):
        self.get_driver().switch_to_window(self.get_driver().window_handles[-1])

    def has_locator(self):
        return self.by_locator is not None

    def has_we_element(self):
        return self.web_element is not None
