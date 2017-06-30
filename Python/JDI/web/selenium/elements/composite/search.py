from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.common.button import Button
from JDI.web.selenium.elements.common.text_field import TextField
from JDI.web.selenium.elements.complex.text_list import TextList


class Search(TextField):
    find_button = None
    suggestions = None

    def __init__(self, by_locator=None, by_select_locator=None, by_suggestions_list_locator=None):
        super(Search, self).__init__(by_locator)
        self.find_button_button = Button(by_select_locator)
        self.suggestions = TextList(by_suggestions_list_locator)

    def get_fields(self, clazz):
        return sorted((list(filter(lambda item: isinstance(item[1], clazz), self.__class__.__dict__.items()))))

    def get_find_button(self):
        find_button = Button(self.get_locator())
        find_button.set_parent(self.get_parent())
        return find_button

    def get_search_button(self):
        if self.get_locator() is not None:
            return self.get_find_button()
        fields = self.get_fields(Button)
        if len(fields) == 0:
            raise Exception("Can't find any buttons on form '{0}'.".format(self.name))
        elif len(fields) == 1:
            return fields[0][1]
        else:
            raise Exception(
                "Form '%s' have more than 1 button. Use submit(entity, buttonName) for this case instead" % self.name)

    def get_search_field(self):
        if self.get_locator() is not None:
            return self.get_text_field()
        fields = self.get_fields(TextField)
        if len(fields) == 0:
            raise Exception("Can't find any text fields on form '{0}'.".format(self.name))
        elif len(fields) == 1:
            return fields[0][1]
        else:
            raise Exception(
                "Form '%s' have more than 1 text field. Use submit(entity, buttonName) for this case instead" % self.name)

    def get_text_field(self):
        text_files = TextField(self.get_locator())
        text_files.set_parent(self.get_parent())
        return text_files

    @scenario(action_name="Search text '%s'", values_list={'value_from_function'})
    def find(self, text):
        self.find_action(text)

    def find_action(self, text):
        self.get_search_field().new_input(text)
        self.get_search_button().click()
