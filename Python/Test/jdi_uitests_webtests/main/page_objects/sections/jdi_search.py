from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.button import Button
from JDI.web.selenium.elements.common.text_field import TextField
from JDI.web.selenium.elements.composite.search import Search


class JDISearch(Search):
    search_input = TextField(By.css(".search-field input"))
    search_button = Button(By.css(".search .search-active .icon-search"))

    def find(self, text):
        Button(By.css(".search>.icon-search")).click()
        super(JDISearch, self).find(text)