from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.base.clickable import Clickable


class Image(Clickable):

    def __init__(self, by_locator=None):
        super(Image, self).__init__(by_locator)

    @scenario(action_name="Get image source for Element")
    def get_source(self):
        return self.get_web_element().get_attribute("src")

    @scenario(action_name="Get image title for Element")
    def get_alt(self):
        return self.get_web_element().get_attribute("alt")