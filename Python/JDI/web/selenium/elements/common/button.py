from JDI.web.selenium.elements.base.clickable_text import ClickableText


class Button(ClickableText):

    def __init__(self, by_locator=None):
        super(Button, self).__init__(by_locator)