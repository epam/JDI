from JDI.web.selenium.elements.complex.selector import Selector


class RadioButtons(Selector):

    def __init__(self, by_options_name_locator_template=None, by_all_options_names_locator=None):
        super(RadioButtons, self).__init__(by_options_name_locator_template, by_all_options_names_locator)