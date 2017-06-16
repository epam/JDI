from enum import Enum

from JDI.web.selenium.elements.base.element import Element


class BaseSelector(Element):


    def __init__(self, by_locator=None):
        super(BaseSelector, self).__init__(by_locator)

    def select_action(self, name):
        el = self.get_element(name)
        if el != None:
            el.click()

    def get_element(self, name):
        elements = self.get_web_elements()
        if isinstance(name, str):
            return self.get_from_list(elements, name)
        elif isinstance(name, Enum):
            return self.get_from_list(elements, name.value)
        else:
            return elements[name-1]

    def get_from_list(self, elements, name):
        return next(x for x in elements if x.text == name)

    def set_value(self, value):
        self.select_action(value)
