from JDI.web.selenium.elements.complex.table.row_column import RowColumn


class Row(RowColumn):

    def __init__(self, val):
        super(Row, self).__init__(val)

    @staticmethod
    def row(val):
        return Row(val)
