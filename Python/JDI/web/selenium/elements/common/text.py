from JDI.web.selenium.elements.base.element import Element


class Text(Element):

    def __init__(self, by_locator=None, web_element=None):
        Element.__init__(by_locator, web_element)
