from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.base.clickable import Clickable


class CheckBox(Clickable):

    def __init__(self, by_locator=None):
        super(CheckBox, self).__init__(by_locator)

    @scenario("Check element")
    def check(self):
        self.check_action()

    def check_action(self):
        if not self.is_check_action():
            self.click()

    @scenario("Uncheck element")
    def uncheck(self):
       self.uncheck_action()

    def uncheck_action(self):
        if self.is_check_action():
            self.click()

    @scenario(action_name="Get is checked")
    def is_checked(self):
        return self.is_check_action()

    @scenario(action_name="Set value to the element")
    def set_value(self, value):
        self.set_value_action(value)

    def set_value_action(self, value):
        if str(value).lower() in ["true", "check", "1"]:
            self.check()
        elif str(value).lower() in ["false", "uncheck", "0"]:
            self.uncheck()

    def is_check_action(self):
        return self.get_element().is_selected()