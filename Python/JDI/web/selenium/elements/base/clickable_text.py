from JDI.web.selenium.elements.base.clickable import Clickable
from JDI.web.selenium.elements.common.text import Text


class ClickableText(Clickable, Text):

    def __init__(self, by_locator=None):
        super(ClickableText, self).__init__(by_locator)
