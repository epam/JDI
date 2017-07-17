from JDI.web.os_action.r_file_input import RFileInput
from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.button import Button
from JDI.web.selenium.elements.common.label import Label
from JDI.web.selenium.elements.common.text_area import TextArea
from JDI.web.selenium.elements.common.text_field import TextField
from JDI.web.selenium.elements.complex.dropdown import Dropdown
from JDI.web.selenium.elements.composite.form import Form
from Test.jdi_uitest_web_examples.main.site.epam.custom_elements.tree_dropdown import TreeDropdown


class AddCVForm(Form):
    first_name = TextField(By.css("[placeholder='First Name']"))
    last_name = TextField(By.css("[placeholder='Last Name']"))
    email = TextField(By.css("[placeholder='Email']"))

    country = Dropdown(root=By.css(".country-wrapper"),
                       by_select_locator=By.css(".arrow"),
                       by_option_locator_template=By.xpath(
                           "*root*//*[contains(@id,'select-box-applicantCountry')]//li[.='%s']"))

    city = Dropdown(root=By.css(".country-wrapper"),
                    by_select_locator=By.css(".arrow"),
                    by_option_locator_all=By.xpath("*root*//*[contains(@id,'select-box-applicantCity')]//li"))

    cv = RFileInput(By.css(".file-upload"))
    comment = TextArea(By.xpath(".comment-input"))

    submut = Button(By.xpath("//*[.='Submit']"))
    cancel = Button(By.xpath("//*[.='Cancel']"))


class JobFilter(Form):
    keyword = TextField(By.css(".job-search-input"))
    category = Dropdown(root=By.css(".multi-select-department"),
                        by_select_locator=By.css(".multi-select-filter"),
                        by_option_locator_all=By.css(".blue-checkbox-label"))

    location = TreeDropdown(By.css(".career-location-box"),
                            [By.css(".location-dropdown .optgroup"), By.xpath("//..//li")])

    select_button = Button(By.css(".job-search-button"))
    label = Label(By.css(".job-search-title"))
