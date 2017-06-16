from JDI.web.selenium.elements.base.element import Element


class Clickable(Element):

    def __init__(self, by_locator=None):
        super(Clickable, self).__init__(by_locator)

    def click(self):
        return self.get_element().click()
