from enum import Enum

from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.complex.radio_buttons import RadioButtons
from JDI.web.selenium.elements.complex.selector import Selector
from JDI.web.selenium.elements.composite.section import Section


class RadioButtonsSummary(RadioButtons):
    def get_selected(self):
        el = list(filter(lambda x: x.is_selected(), self.get_input_web_elements()))
        if len(el) == 0:
            raise Exception("No elements selected. Override getSelectedAction or place locator to <select> tag")
        return el[0].find_element_by_xpath("..").text

    def get_input_web_elements(self):
        return list(map(lambda el: el.find_element_by_tag_name("input"),
                        super(RadioButtonsSummary, self).get_web_elements()))

    def is_selected(self, text):
        actual_text = list(filter(lambda x: x.is_selected(), self.get_input_web_elements()))[0].find_element_by_xpath(
            "..").text
        if isinstance(text, str):
            return actual_text == text
        if isinstance(text, Enum):
            return actual_text == text.value

    def get_selected_index(self):
        try:
            return list(map(lambda x: x.is_selected(), self.get_input_web_elements())).index(True)
        except ValueError:
            raise ValueError("No elements selected. Override getSelectedAction or place locator to <select> tag")


class SelectorSummary(Selector):
    def get_selected(self):
        el = list(filter(lambda x: x.is_selected(), self.get_input_web_elements()))
        if len(el) == 0:
            raise Exception("No elements selected. Override getSelectedAction or place locator to <select> tag")
        return el[0].find_element_by_xpath("..").text

    def get_input_web_elements(self):
        return list(map(lambda el: el.find_element_by_tag_name("input"),
                        super(SelectorSummary, self).get_web_elements()))

    def is_selected(self, text):
        actual_text = list(filter(lambda x: x.is_selected(), self.get_input_web_elements()))[0].find_element_by_xpath(
            "..").text
        if isinstance(text, str):
            return actual_text == text
        if isinstance(text, Enum):
            return actual_text == text.value

    def get_selected_index(self):
        try:
            return list(map(lambda x: x.is_selected(), self.get_input_web_elements())).index(True)
        except ValueError:
            raise ValueError("No elements selected. Override getSelectedAction or place locator to <select> tag")


class Summary(Section):
    odds_radio_buttons = RadioButtonsSummary(By.css("#odds-selector p"))

    odds_selector = SelectorSummary(By.css("#odds-selector p"))
