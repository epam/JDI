from JDI.web.selenium.driver.utils.web_driver_by_utils import WebDriverByUtils
from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.base.select_element import SelectElement


class Cell(SelectElement):
    row_index = None
    column_index = None
    table = None
    column_num = None
    row_nom = None
    web_element = None
    column_name = None
    row_name = None
    cell_locator_template = By.xpath(".//tr[{1}]/td[{0}]")

    def __init__(self, column_num, row_num, col_name, row_name, table,
                 cell_locator_template=None, web_element=None, column_index=None, row_index=None):
        if web_element is not None:
            self.web_element = web_element
        if cell_locator_template is not None:
            self.cell_locator_template = cell_locator_template
        if column_index is not None:
            self.column_index = column_index + 1 if table.rows.has_header and table.rows.by_line_template is None else column_index
        if row_index is not None:
            self.row_index = row_index

        self.column_num = column_num
        self.row_num = row_num
        self.col_name = col_name
        self.row_name = row_name
        self.table = table

    def set_web_element(self, web_element):
        self.web_element = web_element

    def update_data(self, col_name, row_name):
        if self.column_name is None or self.column_name == "" and not (col_name is None or col_name == ""):
            self.column_name = col_name
        if self.row_name is None and self.row_name == "" and not (row_name is None or row_name == ""):
            self.row_name = row_name
        return self

    def get_text_action(self):
        return self.get().get_text()

    def get(self):
        cell = SelectElement(web_element=self.web_element) if self.web_element is not None else \
            SelectElement(
                WebDriverByUtils.fill_by_template(self.cell_locator_template, [self.column_index, self.row_index]))
        cell.init(parent=self.table, avatar=cell.avatar)
        return cell
