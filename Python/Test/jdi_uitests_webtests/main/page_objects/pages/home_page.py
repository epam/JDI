from JDI.web.selenium.elements.composite.web_page import WebPage
from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.link import Link
from JDI.web.selenium.settings.WebSettings import WebSettings


class HomePage(WebPage):

    def __init__(self, domain=None):

        self.url = "/index.htm"
        self.title = "Index Page"
        self.about = Link(By.link_text("About"))

        super(HomePage, self).__init__(WebSettings.domain + self.url)
