from JDI.web.os_action.r_file_input import RFileInput
from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.link import Link
from JDI.web.selenium.elements.composite.web_page import WebPage
from Test.jdi_uitests_webtests.main.page_objects.sections.contact_form import ContactForm


class DatesPage(WebPage):

    def __init__(self, url, title):
        super(DatesPage, self).__init__(url=url, title=title)

    r_image_input = RFileInput(By.css("[data-provides=fileinput]"))

    uploaded_file_name = Link(By.css("[class=filename] span"))

    contact_form = ContactForm(By.css("main form"))