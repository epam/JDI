from JDI.core.settings.jdi_settings import JDISettings
from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.button import Button
from JDI.web.selenium.elements.common.check_box import CheckBox
from JDI.web.selenium.elements.common.label import Label
from JDI.web.selenium.elements.common.text import Text
from JDI.web.selenium.elements.complex.check_list import CheckList
from JDI.web.selenium.elements.complex.combo_box import ComboBox
from JDI.web.selenium.elements.complex.dropdown import Dropdown
from JDI.web.selenium.elements.composite.web_page import WebPage
from Test.jdi_uitests_webtests.main.page_objects.sections.summary import Summary


class CheckBoxMetalColor(CheckBox):
    def is_check_action(self):
        driver = JDISettings.get_driver_factory().get_driver()
        return False if driver.find_element_by_xpath(
            "//*[@id='elements-checklist']//*[*[text()='Water']]/input").get_attribute("checked") is None else True


class CheckListMetalColor(CheckList):
    def is_element_selected(self, el):
        return el.find_element_by_xpath("../input").is_selected()


class ComboBoxMetalColor(ComboBox):
    def get_text_action(self):
        return Text(By.css(".metals .filter-option")).get_text()


class MetalColorPage(WebPage):
    def __init__(self, url, title):
        super(MetalColorPage, self).__init__(url=url, title=title)

    calculate_button = Button(By.id("calculate-button"))

    cb_water = CheckBoxMetalColor(By.xpath("//*[@id='elements-checklist']//*[text()='Water']"))

    calculate_label = Label(By.id("calculate-button"))

    nature_check_list = CheckListMetalColor(By.css("#elements-checklist label"))

    combo_box = ComboBoxMetalColor(select_locator=By.css(".metals .caret"),
                                   options_names_locator_template=By.css(".metals li span"),
                                   value_locator=By.css(".metals input"))

    color_dropdown = Dropdown(By.css(".colors .filter-option"), By.css(".colors li span"))

    summary = Summary(By.id("summary-block"))
