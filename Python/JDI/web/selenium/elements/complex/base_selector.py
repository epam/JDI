from enum import Enum

from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.base.clickable import Clickable
from JDI.web.selenium.elements.base.element import Element
from JDI.web.selenium.elements.complex.text_list import TextList
from JDI.web.selenium.get_element_type import GetElementType


class BaseSelector(Element):
    is_selector = None
    all_labels = None

    def __init__(self, by_option_locator_template=None, by_option_locator_all=None):
        super(BaseSelector, self).__init__(by_option_locator_template)
        if by_option_locator_all is not None:
            self.all_labels = GetElementType(by_option_locator_all, self)


    def is_displayed_in_list(self, num):
        els = self.get_web_elements()
        if num <= 0:
            raise Exception("Can't get option with num '{0}'. Number should be 1 or more".format(num))
        if els is None:
            raise Exception("Can't find option with num '{0}'. Please fix allLabelsLocator".format(num))
        if len(els) < num:
            raise Exception("Can't find option with num '{0}'. Find '{1}' options".format(num, els.size()))
        return els[num].is_displayed()

    def is_selected(self, el):
        return self.is_selected_action(el)

    def is_selected_action(self, el):
        if self.is_selector:
            return el.is_selected()
        attr = el.get_attribute("checked")
        return attr is not None and attr == "true"

    def get_all_labels(self):
        return self.all_labels.get(TextList)

    def get_element(self, name):
        if not self.has_locator() and self.all_labels is None:
            raise Exception("Can't find option '%s'. No optionsNamesLocator and allLabelsLocator found" % name)
        if self.has_locator() and "%s" in self.get_locator()[1]:
            return GetElementType(self.get_locator() % "%s").get(Clickable)
        if self.all_labels is not None:
            return self.get_from_list(self.get_all_labels().avatar.get_elements(), name)
        elements = self.get_web_elements()
        if isinstance(name, str):
            return self.get_from_list(elements, name)
        elif isinstance(name, Enum):
            return self.get_from_list(elements, name.value)
        else:
            return elements[name - 1]

    def get_from_list(self, elements, name):
        return next(x for x in elements if x.text == name)

    def get_options(self):
        return self.get_options_actions()

    @scenario(action_name="Get options(text) of element")
    def get_options_actions(self):
        return list(map(lambda el: el.text, self.get_web_elements()))

    @scenario(action_name="Get value")
    def get_value(self):
        return self.get_value_action()

    def get_value_action(self):
        raise NotImplemented("Need to be implemented in subclasses")

    def select_action(self, name):
        el = self.get_element(name)
        if el is not None:
            el.click()

    @scenario(action_name="Set value")
    def set_value(self, value):
        self.set_value_action(value)

    def set_value_action(self, value):
        self.select_action(value)
