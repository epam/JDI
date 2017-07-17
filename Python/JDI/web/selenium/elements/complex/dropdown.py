from enum import Enum
from numbers import Number

from selenium.webdriver.support.select import Select

from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.base.clickable import Clickable
from JDI.web.selenium.elements.base.element import Element
from JDI.web.selenium.elements.common.label import Label
from JDI.web.selenium.elements.common.text import Text
from JDI.web.selenium.elements.complex.selector import Selector
from JDI.web.selenium.elements.complex.text_list import TextList
from JDI.web.selenium.get_element_type import GetElementType


class Dropdown(Selector, Clickable, Text):

    element = None
    expander = None
    root = None

    def __init__(self, by_select_locator=None, by_option_locator_template=None, by_option_locator_all=None, root=None):
        if by_select_locator is not None and by_option_locator_template is None and by_option_locator_all is None:
            super(Dropdown, self).__init__(by_option_locator_all=by_select_locator)
        elif by_select_locator is not None and by_option_locator_template is not None and by_option_locator_all is None:
            super(Dropdown, self).__init__(by_option_locator_template, by_option_locator_all)
            self.element = GetElementType(by_select_locator, self)
            self.expander = GetElementType(by_select_locator, self)
        elif by_select_locator is not None and by_option_locator_all is not None and by_option_locator_template is None:
            super(Dropdown, self).__init__(by_option_locator_all=by_option_locator_all)
        if root is not None:
            el = Element(by_locator=root)
            el.set_parent(self.get_parent())
            self.set_parent(el)
            self.set_avatar(el.avatar)

    def click_action(self):
        self._element().click()

    @scenario(action_name="Close drop-down")
    def close(self):
        if self.is_displayed_action(1):
            self._element().click()

    @scenario(action_name="Expand Element")
    def expand(self):
        self.expand_action(1)

    def expand_action(self, value):
        if isinstance(value, Number):
           # if not self.is_displayed_in_list(1):
                self._get_expander().avatar.get_element().click()

    def is_displayed_action(self, name):
        el = None
        try:
            el = self.all_labels.get(TextList).get_element(name)
        except:
            return False
        return el is not None and el.is_displayed()

    def get_text_action(self):
        res = ""
        if str(self._element().get_locator()[1]).find("select") != -1:
            res = Select(self._element().get_web_element()).first_selected_option().text
        return res if res != "" and res is not None else self._element().get_text()

    def get_selected_action(self):
        return self.get_text()

    def get_value_action(self):
        return self.get_text()

    def get_options(self):
        is_expanded = self.is_displayed_in_list(1)
        if not is_expanded: self._element().click()
        res = super(Dropdown, self).get_options_actions()
        if not is_expanded: self._element().click()
        return res

    def select_action(self, name):
        if isinstance(name, str):
            if str(self._element().get_locator()[1]).find(" select") != -1:
                try:
                    Select(self._element().get_web_element()).select_by_visible_text(name)
                except:
                    return
            self.expand()
            super(Dropdown, self).select_action(name)
        elif isinstance(name, Enum):
            if str(self._element().get_locator()[1]).find(" select") != -1:
                try:
                    Select(self._element().get_web_element()).select_by_visible_text(name.value)
                except:
                    return
            self.expand()
            super(Dropdown, self).select_action(name.value)
        else:
            if self._element() is not None:
                self.expand()
                super(Dropdown, self).select_action(name)
            else:
                Select(self.get_web_element().select_by_index(name))

    def _element(self):
        if self.element is None:
            return GetElementType(self.get_locator(), self).get(Label) if self.get_locator() is not None and not self.get_locator() == "" else None
        return self.element.get(Label)

    def _get_expander(self):
        if self.expander is None:
            if self.get_locator() is not None and not self.get_locator() == "":
                return GetElementType(self.get_locator(), self).get(Label)
            raise Exception("'expander' element for dropdown not defined")
        return self.expander.get(Label)