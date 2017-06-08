from enum import Enum

from JDI.web.selenium.elements.api_interact.get_element_module import GetElementModule


class BaseElement(object):

    name = None
    parent = None

    def __init__(self, by_locator=None):
        self.avatar = GetElementModule(by_locator, self)
        #self.set_parent()

    def get_driver(self):
        return self.avatar.get_driver()

    def __str__(self):
        return self.__class__.__name__

    def _get_type_name(self):
        return self.__class__.__name__

    # def set_parent(self):
    #     d = self.__class__.__dict__
    #     for element in iter(d):
    #         if hasattr(d[element], "parent"):
    #             d[element].parent = self
