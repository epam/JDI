from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.text_area import TextArea
from JDI.web.selenium.elements.common.text_field import TextField
from JDI.web.selenium.elements.composite.web_page import WebPage


class ContactFormPage(WebPage):

    def __init__(self, url, title):
        super(ContactFormPage, self).__init__(url=url, title=title)

    description = TextArea(By.id("Description"))

    name_text_field = TextField(By.id("Name"))
