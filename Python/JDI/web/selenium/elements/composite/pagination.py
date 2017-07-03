from JDI.core.utils.decorators import scenario
from JDI.web.selenium.elements.base.base_element import BaseElement
from JDI.web.selenium.elements.base.clickable import Clickable
from JDI.web.selenium.get_element_type import GetElementType


class Pagination(BaseElement):
    next_locator = None
    previous_locator = None
    first_locator = None
    last_locator = None

    def __init__(self, by_locator=None,
                 by_next_locator=None, by_previous_locator=None,
                 by_page_template_locator=None, by_first_locator=None, by_last_locator=None):
        if by_locator is not None:
            super(Pagination, self).__init__(by_locator)
        elif by_page_template_locator is not None:
            super(Pagination, self).__init__(by_page_template_locator)
        self.next_locator = by_next_locator
        self.previous_locator = by_previous_locator
        self.first_locator = by_first_locator
        self.last_locator = by_last_locator

    def cant_choose_element_msg(self, action_name, short_name, action):
        return "Can't choose {0} page for Element '{3}'. " \
               "Please specify locator for this action using constructor " \
               "or add Clickable Element on pageObject with name '{1}Link' " \
               "or '{1}Button' or use locator template with parameter '{1}' or override {2}() in class" \
            .format(action_name, short_name, action, self.name)

    def get_clickable(self, name):
        fields = sorted((list(filter(lambda item: isinstance(item[1], Clickable), self.__class__.__dict__.items()))))
        return list(filter(lambda field: name in field[0], fields))[0][1]

    @scenario(action_name="Choose First page")
    def first(self):
        self.first_action()

    def first_action(self):
        short_name = "first"
        if self.next_locator is not None:
            GetElementType(self.first_locator, self).get(Clickable).click()
            return

        first_link = self.get_clickable(short_name)
        if first_link is not None:
            first_link.click()
            return
        raise Exception(self.cant_choose_element_msg("First", short_name, "firstAction"))

    @scenario(action_name="Choose Last page")
    def last(self):
        self.last_action()

    def last_action(self):
        short_name = "last"
        if self.last_locator is not None:
            GetElementType(self.last_locator, self).get(Clickable).click()
            return

        last_link = self.get_clickable(short_name)
        if last_link is not None:
            last_link.click()
            return
        raise Exception(self.cant_choose_element_msg("Last", short_name, "lastAction"))

    @scenario(action_name="Choose Next page")
    def next(self):
        self.next_action()

    def next_action(self):
        short_name = "next"
        if self.next_locator is not None:
            GetElementType(self.next_locator, self).get(Clickable).click()
            return

        new_link = self.get_clickable(short_name)
        if new_link is not None:
            new_link.click()
            return
        raise Exception(self.cant_choose_element_msg("Next", short_name, "nextAction"))

    @scenario(action_name="Choose '%s' page", values_list="value_from_function")
    def select_page(self, index):
        self.select_page_action(index)

    def select_page_action(self, index):
        short_name = "page"
        if self.get_locator() is not None and "%s" in self.get_locator():
            GetElementType(self.get_locator() % index, self).get(Clickable).click()
            return

        last_link = self.get_clickable(short_name)
        if last_link is not None:
            last_link.click()
            return
        raise Exception(self.cant_choose_element_msg(index, short_name, "lastAction"))

    @scenario(action_name="Choose Previous page")
    def previous(self):
        self.previous_action()

    def previous_action(self):
        short_name = "previous"
        if self.next_locator is not None:
            GetElementType(self.previous_locator, self).get(Clickable).click()
            return

        prev_link = self.get_clickable(short_name)
        if prev_link is not None:
            prev_link.click()
            return
        raise Exception(self.cant_choose_element_msg("Previous", short_name, "previousAction"))
