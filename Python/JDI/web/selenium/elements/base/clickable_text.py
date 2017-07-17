from JDI.web.selenium.elements.base.clickable import Clickable
from JDI.web.selenium.elements.common.text import Text


class ClickableText(Clickable, Text):

    def __init__(self, by_locator=None, web_element=None):
        if by_locator is not None:
            super(ClickableText, self).__init__(by_locator=by_locator)
        elif web_element is not None:
            super(ClickableText, self).__init__(web_element=web_element)
        else:
            super(ClickableText, self).__init__()
