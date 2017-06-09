from selenium.webdriver.chrome.webdriver import WebDriver

from JDI.core.settings.jdi_settings import JDISettings
from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.button import Button
from JDI.web.selenium.elements.common.check_box import CheckBox
from JDI.web.selenium.elements.composite.web_page import WebPage


class CheckBoxMetalColor (CheckBox):

    def is_checked(self):
        driver = JDISettings.get_driver_factory().get_driver()
        return False if driver.find_element_by_xpath("//*[@id='elements-checklist']//*[*[text()='Water']]/input").get_attribute("checked") is None else True

class MetalColorPage(WebPage):

    def __init__(self, url, title):
        super(MetalColorPage, self).__init__(url=url, title=title)

    calculate_button = Button(By.id("calculate-button"))

    cb_water = CheckBoxMetalColor(By.xpath("//*[@id='elements-checklist']//*[text()='Water']"))



