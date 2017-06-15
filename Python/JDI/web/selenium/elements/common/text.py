from JDI.web.selenium.elements.base.element import Element


class Text(Element):

    def __init__(self, by_locator=None, web_element=None):
        super(Text, self).__init__(by_locator)

    def get_text(self):
        el = self.get_web_element()
        get_value = el.get_attribute("value")
        get_text = el.text
        return get_value if get_text in [None, ""] else get_text
