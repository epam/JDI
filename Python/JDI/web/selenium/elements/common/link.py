from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.base.clickable_text import ClickableText


class Link(ClickableText):

    def __init__(self, by_locator=None):
        super(Link, self).__init__(by_locator)

    @scenario("Get link reference")
    def get_reference(self):
        return self.get_reference_action()

    def get_reference_action(self):
        return self.get_web_element().get_attribute("href")

    @scenario(action_name="Get link tooltip")
    def get_tooltip(self):
        return self.get_tooltip_action()

    def get_tooltip_action(self):
        return self.get_web_element().get_attribute("title")


