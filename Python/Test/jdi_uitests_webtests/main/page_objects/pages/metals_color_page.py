from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.button import Button
from JDI.web.selenium.elements.composite.web_page import WebPage


class MetalColorPage(WebPage):

    def __init__(self, url, title):
        super(MetalColorPage, self).__init__(url=url, title=title)
        self.calculateButton = Button(By.id("calculate-button"))