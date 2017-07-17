from enum import Enum

from selenium.webdriver import ActionChains

from JDI.core.settings.jdi_settings import JDISettings
from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.complex.selector import Selector
from JDI.web.selenium.settings.web_settings import WebSettings


class Menu(Selector):
    menu_levels_locators = list()
    separator = "|"
    parametrized_class = None

    def __init__(self, by_options_name_locator_template=None,
                 by_all_options_names_locator=None,
                 by_menu_levels_locators=None,
                 parametrized_class=None):
        super(Menu, self).__init__(by_option_locator_template=by_options_name_locator_template,
                                   by_option_locator_all=by_all_options_names_locator)
        if by_all_options_names_locator is not None:
            self.menu_levels_locators.append(by_all_options_names_locator)
        if by_menu_levels_locators is not None:
            if not isinstance(by_menu_levels_locators, list):
                raise TypeError("Please supply 'by_menu_levels_locators' param as list")
            self.menu_levels_locators = by_menu_levels_locators
        if parametrized_class is not None:
            self.parametrized_class = parametrized_class

    @scenario(action_name="Select elements '%s'", values_list={"value_from_function"})
    def hover_and_click(self, names):
        self.hover_and_click_action(names)

    def hover_and_click_action(self, names):
        if names is None or len(names) == 0:
            return
        split = names.split("|")
        if len(split) > len(self.menu_levels_locators):
            msg = "Can't hover and click on element ({0}) by value: {1}. Amount of locators ({2}) " \
                  "less than select path length ({3})".format(str(self), names,
                                                              len(self.menu_levels_locators),
                                                              len(split))
            WebSettings.logger.error(msg)
            raise Exception(msg)
        self.hover(split[0:-1])
        selector = Selector(list(self.menu_levels_locators)[-1])
        selector.set_parent(self.get_parent())
        selector.select(split[-1])

    @scenario(action_name="Hover '%s'", values_list={"name"})
    def hover(self, names):
        if names is None or len(names) == 0:
            return
        self.hover_action(names)

    def hover_action(self, names):
        driver = JDISettings.get_driver_factory().get_driver()
        self._choose_item_action(names, lambda el: ActionChains(driver).move_to_element(el).click_and_hold().perform())

    def _choose_item_action(self, names, action):
        if len(self.menu_levels_locators) == 0 and self.has_locator():
            self.menu_levels_locators.append(self.get_locator())
        if len(self.menu_levels_locators) < len(names): return
        for i in range(0, len(names)):
            els = Selector(list(self.menu_levels_locators)[i]).get_web_elements()
            el = list(filter(lambda x: x.text == names[i], els))[0]
            action(el)

    def select_action(self, name):
        if isinstance(name, Enum):
            self.hover_and_click(name.value)
        else:
            self.hover_and_click(name)
