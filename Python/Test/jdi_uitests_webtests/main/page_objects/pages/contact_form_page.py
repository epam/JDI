from JDI.web.selenium.elements.composite.web_page import WebPage


class ContactFormPage(WebPage):

    def __init__(self, url, title):
        super(ContactFormPage, self).__init__(url=url, title=title)
