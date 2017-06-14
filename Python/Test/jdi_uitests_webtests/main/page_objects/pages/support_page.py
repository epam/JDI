from JDI.web.selenium.elements.composite.web_page import WebPage


class SupportPage(WebPage):

    def __init__(self, url, title):
        super(SupportPage, self).__init__(url=url, title=title)