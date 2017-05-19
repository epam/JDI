from JDI.web.selenium.elements.composite.web_site import WebSite
from tests.site.pageobjects.pages.home_page import HomePage
from JDI.web.selenium.settings.WebSettings import WebSettings


class EpamJDISite(WebSite):

    WebSettings.domain = "https://jdi-framework.github.io/tests"

    home_page = HomePage()

