from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.text_area import TextArea
from JDI.web.selenium.elements.common.text_field import TextField
from JDI.web.selenium.elements.composite.web_page import WebPage
from Test.jdi_uitests_webtests.main.entities.contact import Contact
from Test.jdi_uitests_webtests.main.page_objects.sections.contact_form import ContactForm, ContactFormTwoButtons


class ContactFormPage(WebPage):

    def __init__(self, url, title):
        super(ContactFormPage, self).__init__(url=url, title=title)

    description = TextArea(By.id("Description"))

    name_text_field = TextField(By.id("Name"))

    contact_form = ContactForm(By.css("main form"))

    contact_form_two_buttons = ContactFormTwoButtons(By.css("main form"))

    result = TextArea(By.css(".results"))
