from enum import Enum

from JDI.web.selenium.elements.complex.base_selector import BaseSelector
from JDI.web.selenium.elements.complex.dropdown import Dropdown


class TreeDropdown(Dropdown):
    tree_locators = list()

    def __init__(self, by_select_locator, by_tree_locators=None):
        super(TreeDropdown, self).__init__(by_select_locator=by_select_locator,
                                           by_option_locator_template=by_select_locator)
        self.tree_locators = by_tree_locators

    def select_action(self, names):
        self.expand()
        if isinstance(names, Enum):
            split = list(map(lambda x: x.strip(), names.value.split(">")))
        elif isinstance(names, str):
            split = list(map(lambda x: x.strip(), names.split(">")))
        else:
            raise TypeError("incorrect incorrect format")

        for i in range(0, len(split)):
            el = BaseSelector(by_option_locator_all=self.tree_locators[i])
            el.set_parent(self)

            web_el = el.get_element(split[i])
            if "dropdown-invisible-group" not in web_el.find_element_by_xpath("..").get_attribute("class"):
                web_el.click()
