from JDI.core.settings.jdi_settings import JDISettings
from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.text import Text
from JDI.web.selenium.elements.complex.table.cell import Cell
from JDI.web.selenium.elements.complex.table.columns import Columns
from JDI.web.selenium.elements.complex.table.rows import Rows


class Table(Text):
    cache = True
    footer = list()
    by_cell_locator_template = None
    all_cells = list()
    columns = Columns()
    rows = Rows()
    by_footer_locator = By.xpath(".//tfoot/tr/th")

    def __init__(self, by_table_locator=None,
                 by_column_header=None, by_row_header=None,
                 by_row=None, by_column=None,
                 row_start_index=None, column_start_index=None,
                 by_cell_locator_template=None,
                 by_footer=None, root=None):
        super(Table, self).__init__(by_locator=by_table_locator)

        self.columns.table = self
        self.rows.table = self

        if by_column is not None: self.columns.by_line_template = by_column
        if by_column_header is not None: self.columns.by_headers_locator = by_column_header
        if by_row is not None: self.rows.by_line_template = by_row
        if by_row_header is not None: self.rows.by_headers_locator = by_row_header

        if column_start_index is not None and column_start_index > -1:
            self.columns.start_index = column_start_index
        if row_start_index is not None and row_start_index > -1:
            self.rows.start_index = row_start_index

        self.by_cell_locator_template = by_cell_locator_template
        self.by_footer_locator = by_footer

    def get_text_action(self):
        return "||X||" + "|".join(self.columns.get_headers()) + "||" + "".join(
            list(map(lambda rn: "\n||" + rn + "||" + "|".join(self.row_value(rn)) + "||", self.rows.get_headers())))

    def row_value(self, row_name):
        return self.rows.get_row_value(row_name)

    def get_headers(self):
        return self.columns.get_headers()

    def is_empty(self):
        try:
            self.get_driver().implicitly_wait(0)
            row_count = self.rows.get_count(True)
            return row_count == 0
        finally:
            self.get_driver().implicitly_wait(JDISettings.get_current_timeout_sec())

    def column(self, val, row=None):
        if row is not None:
            column_cell = self.cell(value=val, row=row)
            col = None
            if column_cell is not None:
                num = column_cell.column_num
                col = self.columns.get_column(num)
            return col
        return self.columns.get_column(val)

    def cell(self, web_element=None, column=None, row=None, value=None):
        if None not in [web_element, column, row] and value is None:
            return self.add_cell(web_element,
                                 column.get(lambda name: self.columns.get_headers().index(name), lambda num: num),
                                 row.get(lambda name: self.rows.get_headers().index(name), lambda num: num),
                                 column.get(lambda name: name, lambda num: ""),
                                 row.get(lambda name: name, lambda num: ""))
        elif None not in [value, row] and web_element == column is None:
            row_num = self.rows.get_headers().index(row.name) + 1 if row.has_name() else row.num
            return list(filter(lambda x: x[1].get_value() == value, self.rows.get_row(row_num)))[0][1]
        elif None not in [value, column] and web_element == row is None:
            col_index = self.columns.get_headers().index(column.name)+1 if column.has_name() else column.num
            return list(filter(lambda x: x[1].get_value() == value, self.columns.get_column(col_index)))[0][1]
        else:
            return None

    def add_cell(self, web_element, col_num, row_num, col_name, row_name):
        if web_element is not None:
            cells = list(filter(lambda c: c.column_num == col_num and c.row_num == row_num, self.all_cells))
            cell = cells[0] if len(cells) > 0 else None
            if cell is not None:
                cell.set_web_element(web_element)
                return cell.update_data(col_name, row_name)

            cell = Cell(web_element=web_element, column_num=col_num, row_num=row_num,
                        col_name=col_name, row_name=row_name, cell_locator_template=self.by_cell_locator_template,
                        table=self)
            if self.cache:
                self.all_cells.append(cell)
            return cell

    def row(self, val, column=None):
        if column is not None:
            row_cell = self.cell(value=val, column=column)
            return self.rows.get_row(row_cell.row_num) if row_cell is not None else None
        return self.rows.get_row(val)

    def get_rows(self, *col_name_values):
        if len(col_name_values) == 0:
            return ro
