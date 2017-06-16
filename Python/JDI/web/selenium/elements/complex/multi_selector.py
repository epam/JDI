from JDI.web.selenium.elements.complex.base_selector import BaseSelector


class MultiSelector(BaseSelector):

    def __init__(self, by_locator=None):
        super(MultiSelector, self).__init__(by_locator)

    def select(self, *names):
        self.select_list(names)

    def select_list(self, names):
        for name in names:
            self.select_action(name)

    def select_all(self):
        self.check_all()

    def check(self, *names):
        self.clear()
        for name in names:
            self.select(name)

    def check_all(self):
        for el in self.get_web_elements():
            if not el.is_selected():
                el.click()

    def uncheck_all(self):
        self.clear()

    def get_options(self):
        text_list = list()
        for el in self.get_web_elements():
            text_list.append(el.text if el.text not in [None, ""] else el.value)
        return text_list

    def get_options_as_text(self):
        return ", ".join(self.get_options())

    def get_names(self):
        return self.get_options()

    def get_values(self):
        return self.get_options()

    def clear(self):
        els = self.avatar.get_elements()
        self.clear_elements(els)

    def clear_elements(self, els):
        for el in els:
            if self.is_element_selected(el):
                el.click()

    def is_element_selected(self, el):
        return el.is_selected()

    def are_selected(self):
        selected_names = list()
        for el in self.get_web_elements():
            if self.is_element_selected(el):
                selected_names.append(el.text if el.text not in [None, ""] else el.value)
        return selected_names

    def are_deselected(self):
        deselected_names = list()
        for el in self.get_web_elements():
            if not self.is_element_selected(el):
                deselected_names.append(el.text if el.text not in [None, ""] else el.value)
        return deselected_names

    def get_value(self):
        (", ".join(self.are_selected()))