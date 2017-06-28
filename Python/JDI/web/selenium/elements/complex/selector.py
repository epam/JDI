from enum import Enum

from JDI.web.selenium.elements.complex.base_selector import BaseSelector


class Selector(BaseSelector):

    def __init__(self, by_options_name_locator_template=None, by_all_options_names_locator=None):
        if by_all_options_names_locator is None:
            super(Selector, self).__init__(by_options_name_locator_template)
        else:
            super(Selector, self).__init__(by_options_name_locator_template, by_all_options_names_locator)

    def select(self, name):
        self.select_action(name)

    def get_names(self):
        return self.get_options()

    def get_values(self):
        return self.get_options()

    def get_value(self):
        return self.get_selected()

    def get_options_as_text(self):
        return ", ".join(self.get_options())

    def get_selected(self):
        el = list(filter(lambda x: x.is_selected(), self.get_web_elements()))
        if len(el) == 0:
            raise Exception("No elements selected. Override getSelectedAction or place locator to <select> tag")
        return el[0].text

    def get_selected_index(self):
        index = None
        try:
            list(map(lambda x: x.is_selected(), self.get_web_elements())).index(True)
        except ValueError:
            raise ValueError("No elements selected. Override getSelectedAction or place locator to <select> tag")
        return index

    def is_selected(self, text):
        if isinstance(text, str):
            return self.get_selected() == text
        if isinstance(text, Enum):
            return self.get_selected() == text.value
        else:
            super(Selector, self).is_selected(text)