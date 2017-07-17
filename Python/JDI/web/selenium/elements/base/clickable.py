from JDI.web.selenium.elements.base.element import Element
from JDI.core.utils.decorators import scenario


class Clickable(Element):
    def __init__(self, by_locator=None, web_element=None):
        if by_locator is not None:
            super(Clickable, self).__init__(by_locator=by_locator)
        elif web_element is not None:
            super(Clickable, self).__init__(web_element=web_element)
        else:
            super(Clickable, self).__init__()

    @scenario(action_name="Click on Element for")
    def click(self):
        self.click_action()

    def click_action(self):
        self.get_element().click()
