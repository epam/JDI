from JDI.core.settings.jdi_settings import JDISettings
from JDI.web.selenium.elements.base.base_element import BaseElement


class Element(BaseElement):

    def __init__(self, by_locator=None):
        self.parent = None
        super(Element, self).__init__(by_locator)

    def get_element(self):
        return self.avatar.get_element()

    def set_attribute(self, attribute_name, value):
        JDISettings.get_driver_factory()\
            .get_driver().execute_script("arguments[0].setAttribute('{0}',arguments[1]);".format(attribute_name),
                                         self.get_element(), value)

    def get_web_element(self):
        return self.avatar.get_element()

    def get_web_elements(self):
        return self.avatar.get_elements()
