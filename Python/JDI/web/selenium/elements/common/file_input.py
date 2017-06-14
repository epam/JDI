from JDI.web.selenium.elements.common.text_field import TextField


class FileInput(TextField):

    def __init__(self, by_locator=None, by_label=None, web_element=None):
        super(FileInput, self).__init__(by_locator)

    def set_value_action(self, value):
        self.input(value)