from JDI.web.selenium.elements.base.element import Element
from JDI.web.selenium.elements.complex.multi_selector import MultiSelector


class CheckList(MultiSelector):


    def __init__(self, by_locator=None):
        super(CheckList, self).__init__(by_locator)