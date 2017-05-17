from JDI.web.selenium.elements.base.clickable import Clickable


class ClickableText(Clickable):

    def __init__(self, by_locator=None):
        super(ClickableText, self).__init__(by_locator)

