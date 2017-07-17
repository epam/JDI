from numbers import Number

import numpy as np

from JDI.web.selenium.driver.utils.web_driver_by_utils import WebDriverByUtils
from JDI.web.selenium.elements.base.element import Element


class TableLine(Element):

    not_implemented_message = "Method should be implemented in sub-classes (Rows and Columns)"

    has_header = None
    element_index = None
    table = None
    count = 0
    headers = list()
    start_index = 1
    by_headers_locator = None
    by_default_template = None
    by_line_template = None

    def get_headers(self):
        if len(self.headers) != 0:
            return self.headers
        local_headers = self.get_headers_text_action() if self.has_header else list(map(lambda x: str(x+1), np.arange(self.get_count(True))))
        if local_headers is None or len(local_headers) == 0:
            return list()
        if 0 < self.count < len(local_headers):
            local_headers = local_headers[0:self.count]
        self.set_headers(local_headers)
        self.set_count(len(local_headers))
        return local_headers

    def get_headers_text_action(self):
        return list(map(lambda x: x.text, self.get_headers_action()))

    def get_headers_action(self):
        raise NotImplementedError(self.not_implemented_message)

    def set_headers(self, value):
        if self.table.cache:
            self.headers = list(value)

    def set_count(self, value):
        if self.table.cache:
            self.count = value

    def get_line_action(self, line_name):
        if isinstance(line_name, str):
            index = self.get_headers().index(line_name) + 1
            if self.by_line_template is not None and "%s" in self.by_line_template[1]:
                return self.get_element_by_template(index)
            return self.get_line_action(index) if self.by_line_template is None else self.get_element_by_template(index)
        elif isinstance(line_name, int):
            return self.get_element_by_template(line_name + self.start_index - 1)

    def get_element_by_template(self, value):
        locator = WebDriverByUtils.fill_by_template(
            self.by_line_template if self.by_line_template is not None else self.by_default_template, value)
        return list(filter(lambda el: el.is_displayed(), self.table.get_web_element().find_elements(locator[0], locator[1])))

    def get_count(self, accept_empty=True):
        if self.count > 0: return self.count
        if self.headers is not None and len(self.headers) > 0:
            return self.headers

        els = self.get_headers_action()
        if len(els) == 0: els = self.get_first_line()
        if not accept_empty: 
            els = list(map(lambda x: x is not None and len(x) > 0, self.get_first_line()))
        return len(els) if els is not None and len(els) > 0 else 0

    def get_first_line(self):
        raise NotImplementedError(self.not_implemented_message)
