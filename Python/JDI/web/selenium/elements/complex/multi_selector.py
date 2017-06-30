from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.complex.base_selector import BaseSelector


class MultiSelector(BaseSelector):
    def __init__(self, by_locator=None):
        super(MultiSelector, self).__init__(by_locator)

    @scenario(action_name="Are deselected")
    def are_deselected(self):
        deselected_names = list()
        for el in self.get_web_elements():
            if not self.is_element_selected(el):
                deselected_names.append(el.text if el.text not in [None, ""] else el.value)
        return deselected_names

    @scenario(action_name="Are selected")
    def are_selected(self):
        selected_names = list()
        for el in self.get_web_elements():
            if self.is_element_selected(el):
                selected_names.append(el.text if el.text not in [None, ""] else el.value)
        return selected_names

    def check(self, *names):
        self.clear()
        for name in names:
            self.select(name)

    def check_all(self):
        for el in self.get_web_elements():
            if not el.is_selected():
                el.click()

    @scenario(action_name="Clear Options")
    def clear(self):
        self.clear_action()

    def clear_action(self):
        els = self.avatar.get_elements()
        self.clear_elements(els)

    def clear_elements(self, els):
        for el in els:
            if self.is_element_selected(el):
                el.click()

    def is_element_selected(self, el):
        return el.is_selected()

    def get_names(self):
        return self.get_options()

    def get_options_as_text(self):
        return ", ".join(self.get_options())

    def get_values(self):
        return self.get_options()

    def get_value_action(self):
        (", ".join(self.are_selected()))

    @scenario(action_name="Select '%s'", values_list={"value_from_function"})
    def select(self, *names):
        self.select_list_action(names)

    def select_list_action(self, names):
        for name in names:
            self.select_action(name)

    def select_all(self):
        self.check_all()

    def set_value_action(self, value):
        self.select_list_action(value.split(", "))

    def uncheck_all(self):
        self.clear()
