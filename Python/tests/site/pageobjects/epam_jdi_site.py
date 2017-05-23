from JDI.web.selenium.elements.composite.web_site import WebSite
from tests.site.pageobjects.pages import *
from JDI.web.selenium.settings.WebSettings import WebSettings
from JDI.web.selenium.elements.api_interact.find_element_by import By


class EpamJDISite(WebSite):

    WebSettings.domain = "https://jdi-framework.github.io/tests"

    home_page = HomePage()

    login_page = Login(By.css(".uui-profile-menu"))

