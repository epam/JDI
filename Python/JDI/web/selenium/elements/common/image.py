from JDI.web.selenium.elements.base.clickable import Clickable


class Image(Clickable):

    def __init__(self, by_locator=None):
        super(Image, self).__init__(by_locator)

    def get_source(self):
        return self.get_web_element().get_attribute("src")

    def get_alt(self):
        return self.get_web_element().get_attribute("alt")