from JDI.web.selenium.elements.base.base_element import BaseElement


class Element(BaseElement):

    def __init__(self, by_locator=None):
        self.parent = None
        super(Element, self).__init__(by_locator)

    def get_element(self):
        return self.avatar.get_element()


