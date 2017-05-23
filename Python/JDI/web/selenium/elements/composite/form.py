from JDI.web.selenium.elements.base.element import Element


class Form(Element):

    def submit(self, entity):
        self.fill(Form.get_map_from_object(entity))
        self.get_button("submit").click()

    @staticmethod
    def get_map_from_object(obj):
        return obj.__dict__

    def fill(self, entity_map):
        fields = self.get_fields(entity_map)
        for field, element in fields.items():
            element.get_element().send_keys(entity_map[field])

    def get_fields(self, entity_map):
        fields = {field: element for field, element in self.__class__.__dict__.items() if field in entity_map
                  and "TextField" in str(type(element))}
        return fields

    def get_button(self, button_name):
        button = [element for element in self.__class__.__dict__.values() if "Button" in str(type(element))][0]
        b = button.get_element()
        return b


