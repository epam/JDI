from JDI.web.selenium.elements.base.base_element import BaseElement


class Element(BaseElement):

    def __init__(self, by_locator=None, web_element=None):
        BaseElement.__init__(by_locator)

