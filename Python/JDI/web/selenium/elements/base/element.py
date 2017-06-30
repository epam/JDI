from selenium.webdriver import ActionChains

from JDI.core.settings.jdi_settings import JDISettings
from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.base.base_element import BaseElement


class Element(BaseElement):

    def __init__(self, by_locator=None):
        self.parent = None
        super(Element, self).__init__(by_locator)

    def get_element(self):
        return self.avatar.get_element()

    @scenario(action_name="Set Attribute '%s'='%s", values_list={"2_values_from_function"})
    def set_attribute(self, attribute_name, value):
        JDISettings.get_driver_factory()\
            .get_driver().execute_script("arguments[0].setAttribute('{0}',arguments[1]);".format(attribute_name),
                                         self.get_element(), value)

    @scenario(action_name="Get web element")
    def get_web_element(self):
        return self.avatar.get_element()

    def get_web_elements(self):
        return self.avatar.get_elements()

    def get_locator(self):
        return self.avatar.by_locator

    @scenario(action_name="Is element displayed")
    def is_displayed(self):
        return self.is_displayed_action()

    @scenario(action_name="Is element hidden")
    def is_hidden(self):
        return not self.is_displayed_action()

    @scenario(action_name="Right click on Element")
    def right_click(self):
        driver = JDISettings.get_driver_factory().get_driver()
        ActionChains(driver).context_click(self.get_web_element()).perform()

    @scenario(action_name="Click in Center of Element")
    def click_center(self):
        driver = JDISettings.get_driver_factory().get_driver()
        ActionChains(driver).click(self.get_web_element()).perform()

    @scenario(action_name="Move mouse over Element")
    def mouse_over(self):
        driver = JDISettings.get_driver_factory().get_driver()
        ActionChains(driver).move_to_element(self.get_web_element()).perform()

    @scenario(action_name="Focus on Element")
    def focus(self):
        self.focus_action()

    def focus_action(self):
        size = self.get_web_element().size
        driver = JDISettings.get_driver_factory().get_driver()
        ActionChains(driver).move_to_element_with_offset(self.get_web_element(), size["width"] / 2, size["height"] / 2).perform()

    @scenario(action_name="Drag and drop to Target Element: %s", values_list="value_from_function")
    def drag_and_drop(self, target):
        driver = JDISettings.get_driver_factory().get_driver()
        ActionChains(driver).drag_and_drop(self.get_web_element(), target.get_web_element()).perform()

    def is_displayed_action(self):
        return self.get_web_element().is_displayed()
