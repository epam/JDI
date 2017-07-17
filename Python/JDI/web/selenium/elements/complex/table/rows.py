from JDI.core.interfaces.element_index_type import ElementIndexType
from JDI.web.selenium.driver.timer import Time
from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.complex.table.column import Column
from JDI.web.selenium.elements.complex.table.row import Row
from JDI.web.selenium.elements.complex.table.table_line import TableLine


class Rows(TableLine):
    def __init__(self):
        self.has_header = False
        self.element_index = ElementIndexType.nums
        self.by_headers_locator = By.xpath(".//tr/td[1]")
        self.by_default_template = By.xpath(".//tr[%s]/td")

    def get_headers_action(self):
        return self.table.get_web_element().find_elements(self.by_headers_locator[0], self.by_headers_locator[1])

    def get_row_value(self, row_name):
        try:
            return list(map(lambda el: el.text, self.get_line_action(row_name)))
        except Exception as ex:
            raise ex

    def get_first_line(self):
        return self.table.columns.get_line_action(1)

    def get_row(self, val):
        try:
            cols_count = self.table.columns.get_count()
            web_row = Time.wait_result_by_conditions(lambda: self.get_line_action(val),
                                                        lambda els: len(els) == cols_count)
            res = list()
            if isinstance(val, int):
                if val <= 0:
                    raise Exception("Table indexes starts from 1")
                if self.get_count() < 0 or self.get_count() < val or val <=0:
                    raise Exception("Can't Get Row '{0}'. [num] > ColumnsCount({1}).".format(val, self.get_count()))
                for i in range(cols_count):
                    res.append(tuple([self.table.columns.get_headers()[i],self.table.cell(web_row[i], Column(i+1), Row(val))]))
            elif isinstance(val, str):
                    headers = self.table.columns.get_headers()
                    res = list()
                    for i in range(cols_count):
                        res.append(tuple([self.table.columns.get_headers()[i],self.table.cell(web_row[i], Column(headers[i]), Row(i))]))
            else:
                raise ValueError("Value for 'get_row' should string or int")
            return res
        except Exception as ex:
                raise Exception(val + " " + str(ex))

    #def get(self):
    #    return map(lambda self.get_headers())


