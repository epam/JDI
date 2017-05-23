from JDI.web.selenium.elements.base.element import Element


class Text(Element):

    def __init__(self, by_locator=None, web_element=None):
        super(Text, self).__init__(by_locator)
