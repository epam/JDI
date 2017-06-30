from JDI.core.settings.jdi_settings import JDISettings
from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.common.text_field import TextField


class TextArea(TextField):
    def __init__(self, by_locator=None, by_label=None, web_element=None):
        super(TextArea, self).__init__(by_locator)

    @scenario("Input several lines of text in textarea")
    def input_lines(self, lines):
        self.clear_action()
        for i in range(0, len(lines) - 1):
            self.input_action(lines[i] + "\n")
        self.input_action(lines[-1])

    @scenario(action_name="Get text as lines")
    def get_lines(self):
        return JDISettings.get_driver_factory() \
            .get_driver().execute_script("return arguments[0].value", self.get_element()).split("\n")

    @scenario(action_name="Add text from new line in textarea")
    def add_new_line(self, text):
        self.input_action("\n")
        self.input_action(text)