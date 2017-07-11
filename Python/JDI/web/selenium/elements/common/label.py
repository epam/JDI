from JDI.web.selenium.elements.base.clickable_text import ClickableText


class Label(ClickableText):

    def __init__(self, by_locator=None):
        super(Label, self).__init__(by_locator)