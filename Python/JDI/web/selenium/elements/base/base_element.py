from JDI.web.selenium.elements.api_interact.get_element_module import GetElementModule


class BaseElement(object):

    def __init__(self, by_locator=None):
        self.avatar = GetElementModule(by_locator)

    def get_driver(self):
        return self.avatar.get_driver()

    def _get_type_name(self):
        return self.__class__.__name__
