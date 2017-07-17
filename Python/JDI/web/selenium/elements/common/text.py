import re

from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.actions.waiter import waiter_decorator
from JDI.web.selenium.elements.base.element import Element

class Text(Element):

    def __init__(self, by_locator=None, web_element=None):
        if by_locator is not None:
            super(Text, self).__init__(by_locator=by_locator)
        elif web_element is not None:
            super(Text, self).__init__(web_element=web_element)
        else:
            super(Text, self).__init__()

    def get_text_action(self):
        el = self.get_web_element()
        res = el.get_attribute("value") if el.text in [None, ""] else el.text
        return "" if res is None else res

    @scenario(action_name="Get text")
    def get_text(self):
        return self.get_text_action()

    @scenario(action_name="Get value")
    def get_value(self):
        return self.get_text_action()

    @scenario(action_name="Wait text contains '%s'", values_list={"value_from_function"})
    @waiter_decorator
    def wait_match_text(self, reg_exp):
        return re.search(reg_exp, self.get_text_action()) is not None

    @scenario(action_name="Wait text match regex '%s'", values_list={"value_from_function"})
    @waiter_decorator
    def wait_contain_text(self, text):
        return self.get_text_action().find(text) != -1


