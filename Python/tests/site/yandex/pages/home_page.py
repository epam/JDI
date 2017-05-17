from JDI.web.selenium.elements.composite.web_page import WebPage


class HomePage(WebPage):

    url = "/"

    def __init__(self, domain):
        url = domain + self.url
        super(HomePage, self).__init__(url)

