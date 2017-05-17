from JDI.core.settings.jdi_settings import JDISettings


class GetElementModule(object):

    FAILED_TO_FIND_ELEMENT_MESSAGE = "Can't find Element '%s' during %s seconds"
    FIND_TO_MUCH_ELEMENTS_MESSAGE = "Find %s elements instead of one for Element '%s' during %s seconds"

    logger = JDISettings.logger

    by_locator = None
    web_element = None
    element = None
    frameLocator = None
    web_elements = []
    find_element_function = None
    find_elements_function = None

    def __init__(self, by_locator=None, element=None):  # element -> table, search, button ...
        self.by_locator = by_locator
        self.element = element

    def get_element(self):
        # self.logger.debug("Get Web Element: " + self.element)
        element = self.web_element if self.web_element is not None else self.__get_element_action()
        # self.logger.debug("One Element found")
        return element

    def __get_element_action(self):
        result = self.__get_one_or_more_elements()
        if len(result) == 0:
            raise Exception(GetElementModule.FAILED_TO_FIND_ELEMENT_MESSAGE % self.element)
        elif len(result) == 1:
            return result[0]
        else:
            raise Exception(GetElementModule.FIND_TO_MUCH_ELEMENTS_MESSAGE % len(result), self.element)

    def __get_one_or_more_elements(self):
        return self.web_elements if self.web_elements else self.__search_elements()

    def __search_elements(self):
        # TODO: containsRoot, searchContext
        locator = self.by_locator
        driver = self.get_driver()
        return driver.find_elements(locator[0], locator[1])

    @staticmethod
    def get_driver():
        return JDISettings.get_driver_factory().get_driver()

