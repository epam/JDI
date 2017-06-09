from JDI.web.selenium.elements.base.clickable import Clickable


class CheckBox(Clickable):

    def __init__(self, by_locator=None):
        super(CheckBox, self).__init__(by_locator)

    def check(self):
        if not self.is_checked():
            self.click()

    def uncheck(self):
        if self.is_checked():
            self.click()

    def is_checked(self):
        return self.get_element().is_selected()

    def set_value(self, value):
        if value.lower() in ["true", "1", "check"]: self.check()
        elif value.lower() in ["false", "0", "uncheck"]: self.uncheck()
        else: raise Exception("Value {0} could not be set to the checkbox".format(value))


