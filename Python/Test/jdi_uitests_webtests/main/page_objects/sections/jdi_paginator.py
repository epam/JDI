from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.button import Button
from JDI.web.selenium.elements.composite.pagination import Pagination


class JDIPagination(Pagination):
    next_button = Button(By.css("[class=next]  a"))
    previous_button = Button(By.css("[class=prev]  a"))
    first_button = Button(By.css("[class=first] a"))
    last_button = Button(By.css("[class=last]  a"))
    page = Button(By.css(".uui-pagination li"))
