from selenium.webdriver.remote.webelement import WebElement

from JDI.web.selenium.elements.base.element import Element


class TextList(Element):

    def __init__(self, by_locator=None):
        super(TextList, self).__init__(by_locator)

    def get_first_text(self):
        return self.get_text_list()[0]

    def get_text_by_line(self, line_number):
        return  self.get_text_list()[line_number]

    def get_text_list(self):
        res = list()
        for x in self.get_web_elements():
            res.append(x.text)
        return res

