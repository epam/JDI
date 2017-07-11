from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.common.text import Text


class TextField(Text):

    def __init__(self, by_locator=None, by_label=None, web_element=None):
        super(TextField, self).__init__(by_locator)

    @scenario(action_name="Input text")
    def input(self, text):
        self.input_action(text)

    def input_action(self,text):
        self.get_web_element().send_keys(text)

    @scenario(action_name="Send keys to the element")
    def send_keys(self, text):
        self.input(text)

    def new_input(self, text):
        self.clear()
        self.input(text)

    @scenario(action_name="Clear text field")
    def clear(self):
        self.clear_action()

    def clear_action(self):
        self.get_web_element().clear()

    def focus_action(self):
        self.get_web_element().click()