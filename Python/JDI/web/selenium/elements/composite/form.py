from enum import Enum

from JDI.jdi_assert.testing.assertion import Assert
from JDI.web.selenium.elements.common.text_area import TextArea
from JDI.web.selenium.elements.common.text_field import TextField
from JDI.web.selenium.elements.composite.section import Section


class Form(Section):
    entity_class = None

    def __init__(self, by_locator, entity_class=None):
        self.entity_class = entity_class
        super(Form, self).__init__(by_locator)

    def check(self, entity):
        res = self.verify(entity)
        Assert.is_true(res, "Check form failed")

    def fill(self, entity):
        entity_map = Form.get_map_from_object(entity)
        fields = self.get_fields(entity_map)
        for field in fields:
            field.get_web_element().send_keys(entity_map[field.name])

    def get_button(self, button_name):
        return [element for element in self.__class__.__dict__.values() if element.__class__.__name__ is "Button"][0]

    def get_fields(self, entity_map):
        return map(lambda item: item[1],
                   filter(
                       lambda item: item[0] in entity_map and isinstance(item[1], TextField) or isinstance(item[1],
                                                                                                           TextArea),
                       self.__class__.__dict__.items()))

    @staticmethod
    def get_map_from_object(obj):
        return obj.__dict__

    def set_text(self, text):
        field = sorted((list(filter(lambda item: isinstance(item[1], TextField) or isinstance(item[1], TextArea),
                                    self.__class__.__dict__.items()))))[0][1]
        field.send_keys(text)

    def submit_form(self, entity, button_name="submit"):
        if isinstance(entity, str):
            self.set_text(entity)
        else:
            self.fill(entity)
        if isinstance(button_name, Enum):
            button_name = button_name.value
        self.get_button(button_name).click()

    def verify(self, entity):
        entity_map = Form.get_map_from_object(entity)
        for field in self.get_fields(entity_map):
            if field.get_text() != entity_map[field.name]:
                return False
        return True
