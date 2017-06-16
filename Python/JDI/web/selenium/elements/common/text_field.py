from JDI.web.selenium.elements.common.text import Text


class TextField(Text):

    def __init__(self, by_locator=None, by_label=None, web_element=None):
        super(TextField, self).__init__(by_locator)

    def input(self, text):
        self.get_web_element().send_keys(text)

    def send_keys(self, text):
        self.input(text)

    def new_input(self, text):
        self.clear()
        self.input(text)

    def clear(self):
        self.get_web_element().clear()

    def focus(self):
        self.get_web_element().click()