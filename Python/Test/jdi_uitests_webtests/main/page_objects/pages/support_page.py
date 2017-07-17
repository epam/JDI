from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.complex.table.table import Table
from JDI.web.selenium.elements.composite.web_page import WebPage


class SupportPage(WebPage):

    def __init__(self, url, title):
        super(SupportPage, self).__init__(url=url, title=title)

    support_table = Table(by_table_locator=By.css(".uui-table"))
    support_table.rows.start_index = 1