from JDI.web.selenium.elements.common.image import Image
from JDI.web.selenium.elements.common.text import Text
from JDI.web.selenium.elements.composite.web_page import WebPage
from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.link import Link


class HomePage(WebPage):

    def __init__(self, url, title):
        super(HomePage, self).__init__(url=url, title=title)

    about = Link(By.link_text("About"))

    logo_image = Image(By.css(".epam-logo img"))

    text_item = Text(By.css(".main-txt"))