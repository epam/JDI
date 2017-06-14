from JDI.web.selenium.elements.base.clickable_text import ClickableText


class Link(ClickableText):

    def __init__(self, by_locator=None):
        super(Link, self).__init__(by_locator)

    def get_reference(self):
        return self.get_web_element().get_attribute("href")

   # def wait_reference_contains(self, text):


