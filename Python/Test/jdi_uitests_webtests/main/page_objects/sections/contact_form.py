from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.button import Button
from JDI.web.selenium.elements.common.text_area import TextArea
from JDI.web.selenium.elements.common.text_field import TextField
from JDI.web.selenium.elements.composite.form import Form


class ContactForm(Form):
    def __init__(self, by_locator=None):
        super(ContactForm, self).__init__(by_locator)

    first_name = TextField(By.id("Name"))
    last_name = TextField(By.id("LastName"))
    description = TextArea(By.id("Description"))

    submit = Button(By.xpath("//*[text()='Submit']"))

    def get_form_value(self):
        return {self.first_name.get_text(), self.last_name.get_text(), self.description.get_text()}


class ContactFormTwoButtons(Form):
    def __init__(self, by_locator=None):
        super(ContactFormTwoButtons, self).__init__(by_locator)

    first_name = TextField(By.id("Name"))
    last_name = TextField(By.id("LastName"))
    description = TextArea(By.id("Description"))

    submit = Button(By.xpath("//*[text()='Submit']"))
    calculate = Button(By.xpath("//*[text()='Calculate']"))

    def get_form_value(self):
        return {self.first_name.get_text(), self.last_name.get_text(), self.description.get_text()}