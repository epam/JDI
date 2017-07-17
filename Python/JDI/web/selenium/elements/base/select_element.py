from JDI.web.selenium.elements.base.clickable_text import ClickableText


class SelectElement(ClickableText):
    
    def __init__(self, by_locator=None, web_element=None):
        if by_locator is not None:
            super(SelectElement, self).__init__(by_locator=by_locator)
        elif web_element is not None:
            super(SelectElement, self).__init__(web_element=web_element)
        else:
            super(SelectElement, self).__init__()