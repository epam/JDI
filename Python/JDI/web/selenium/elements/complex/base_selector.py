from enum import Enum

from JDI.web.selenium.elements.base.element import Element
from JDI.web.selenium.get_element_type import GetElementType


class BaseSelector(Element):

    is_selector = None
    all_labels = None

    def __init__(self, by_options_name_locator=None, by_all_labels_locator=None):
        super(BaseSelector, self).__init__(by_options_name_locator)
        if by_all_labels_locator is not None:
            self.all_labels = GetElementType(by_all_labels_locator, self)


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
        self.set_value_action(value)

    def set_value_action(self, value): self.select_action(value)
    def get_options(self): raise NotImplemented("This method should be implemented in subclasses")

    def get_options_actions(self):
        els = self.get_web_elements()
        res = list()
        for el in els: res.append(el.text)
        return res

    def is_selected(self, el):
        if self.is_selector:
            return el.is_selected()
        attr = el.get_attribute("checked")
        return attr is not None and attr == "true"

    def is_displayed_in_list(self, num):
        els = self.get_web_elements()
        if num <= 0:
            raise Exception("Can't get option with num '{0}'. Number should be 1 or more".format(num))
        if els is None:
            raise Exception("Can't find option with num '{0}'. Please fix allLabelsLocator".format(num))
        if len(els) < num:
            raise Exception("Can't find option with num '{0}'. Find '{1}' options".format(num, els.size()))
        return els[num].is_displayed()