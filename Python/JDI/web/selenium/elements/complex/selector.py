from enum import Enum

from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.complex.base_selector import BaseSelector


class Selector(BaseSelector):
    def __init__(self, by_option_locator_template=None, by_option_locator_all=None):
        if by_option_locator_all is None:
            super(Selector, self).__init__(by_option_locator_template=by_option_locator_template)
        else:
            super(Selector, self).__init__(by_option_locator_template=by_option_locator_template,
                                           by_option_locator_all=by_option_locator_all)

    def is_selected_action(self, el):
        if isinstance(el, str):
            return self.get_selected() == el
        if isinstance(el, Enum):
            return self.get_selected() == el.value
        else:
            super(Selector, self).is_selected(el)

    def get_names(self):
        return self.get_options()

    def get_options_as_text(self):
        return ", ".join(self.get_options())

    @scenario(action_name="Get Selected element name")
    def get_selected(self):
        return self.get_selected_action()

    def get_selected_action(self):
        el = list(filter(lambda x: x.is_selected(), self.get_web_elements()))
        if len(el) == 0:
            raise Exception("No elements selected. Override getSelectedAction or place locator to <select> tag")
        return el[0].text

    @scenario(action_name="Get Selected element index")
    def get_selected_index(self):
        try:
            index = self.get_selected_index_action()
            if index is None:
                raise ValueError("No elements selected. Override getSelectedAction or place locator to <select> tag")
            else:
                return index
        except ValueError:
            raise ValueError("No elements selected. Override getSelectedAction or place locator to <select> tag")

    def get_selected_index_action(self):
        return list(map(lambda x: x.is_selected(), self.get_web_elements())).index(True)

    def get_values(self):
        return self.get_options()

    def get_value_action(self):
        return self.get_selected()

    @scenario(action_name="Select")
    def select(self, name):
        self.select_action(name)
