from JDI.web.selenium.elements.base.base_element import BaseElement


class WebPage(BaseElement):

    def __init__(self, url):
        self.url = url
        super(WebPage, self).__init__()

    def open(self):
        self.get_driver().get(self.url)

