from JDI.web.selenium.elements.composite.web_site import WebSite
from JDI.web.selenium.settings.WebSettings import WebSettings
from JDI.web.selenium.elements.api_interact.find_element_by import By
from Test.jdi_uitests_webtests.main.page_objects.pages import *


class EpamJDISite(WebSite):

    WebSettings.domain = "https://jdi-framework.github.io/tests"

    home_page = HomePage()

    login_page = Login(By.css(".uui-profile-menu"))

