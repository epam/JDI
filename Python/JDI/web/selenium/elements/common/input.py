from JDI.web.selenium.elements.common.text_field import TextField


class Input(TextField):

    def __init__(self, by_locator=None, web_element=None):
        TextField.__init__(by_locator, web_element)

