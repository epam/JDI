from JDI.web.selenium.elements.common.text_field import TextField
from JDI.web.selenium.elements.complex.dropdown import Dropdown
from JDI.web.selenium.get_element_type import GetElementType


class ComboBox(Dropdown):

    text_field = None

    def __init__(self, select_locator=None, options_names_locator_template=None, value_locator=None):
        super(ComboBox, self).__init__(select_locator, options_names_locator_template)
        self.text_field = GetElementType(value_locator, self) if value_locator is not None else GetElementType(select_locator, self)

    def get_options(self):
        is_expanded = self.is_displayed_action(1)
        if not is_expanded:
            self._element().click()
        res = super(ComboBox, self).get_options_actions()
        if not is_expanded:
            self._element().click()
        return res

    def set_value_action(self, value):
        self.clear()
        self.input(value)

    def clear(self):
        self.text_field.get(TextField).clear()

    def input(self, value):
        self.text_field.get(TextField).input(value)