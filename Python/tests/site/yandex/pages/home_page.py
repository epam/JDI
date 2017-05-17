from JDI.web.selenium.elements.composite.web_page import WebPage
from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.link import Link


class HomePage(WebPage):

    url = "/"

    yandex_link = Link(By.css(".layout__footer .b-table__cell a.link"))

    def __init__(self, domain):
        url = domain + self.url
        super(HomePage, self).__init__(url)

