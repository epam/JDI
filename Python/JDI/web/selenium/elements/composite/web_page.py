from JDI.web.selenium.elements.base.base_element import BaseElement


class WebPage(BaseElement):

    def __init__(self, url):
        self.url = url

    def open(self):
        self.get_driver().get(self.url)

