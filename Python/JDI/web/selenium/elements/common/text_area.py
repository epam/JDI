from JDI.core.settings.jdi_settings import JDISettings
from JDI.web.selenium.elements.common.text_field import TextField


class TextArea(TextField):
    def __init__(self, by_locator=None, by_label=None, web_element=None):
        super(TextArea, self).__init__(by_locator)

    def input_lines(self, lines):
        self.clear()
        for i in range(0, len(lines) - 1):
            self.input(lines[i] + "\n")
        self.input(lines[-1])

    def get_lines(self):
        return JDISettings.get_driver_factory() \
            .get_driver().execute_script("return arguments[0].value", self.get_element()).split("\n")

    def add_new_line(self, text):
        self.input("\n")
        self.input(text)