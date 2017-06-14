from JDI.web.os_action.jdi_win32 import jdi_win32
from JDI.web.selenium.elements.common.file_input import FileInput


class RFileInput(FileInput):

    def __init__(self, by_locator=None, by_label=None, web_element=None):
        super(RFileInput, self).__init__(by_locator)

    def clear(self):
        pass

    def input(self, text):
        self.get_web_element().click()
        jdi_win32.paste_text(text)

