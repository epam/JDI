from JDI.web.selenium.elements.composite.web_site import WebSite
from JDI.web.selenium.elements.api_interact.find_element_by import By
from Test.jdi_uitests_webtests.main.page_objects.pages import *
from Test.jdi_uitests_webtests.main.page_objects.pages.metals_color_page import MetalColorPage


class EpamJDISite(WebSite):

    home_page = HomePage(url="/index.htm", title="Index Page")

    metals_colors_page = MetalColorPage(url="/page2.htm", title="Metal and Colors")

    login_page = Login(By.css(".uui-profile-menu"))

 #   @JPage(url = "/page2.htm", title = "Metal and Colors")
 #   public static MetalsColorsPage metalsColorsPage;

