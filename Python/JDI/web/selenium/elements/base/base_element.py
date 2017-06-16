from enum import Enum

from JDI.web.selenium.elements.api_interact.get_element_module import GetElementModule


class BaseElement(object):

    name = None
    parent = None

    def __init__(self, by_locator=None):
        self.avatar = GetElementModule(by_locator, self)

    def get_driver(self):
        return self.avatar.get_driver()

    def __str__(self):
        return self.__class__.__name__

    def _get_type_name(self):
        return self.__class__.__name__

    def get_name(self):
        return self.name if self.name is not None else self._get_type_name()
