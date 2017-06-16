from JDI.web.selenium.elements.complex.text_list import TextList
from JDI.web.selenium.elements.composite.web_site import WebSite
from JDI.web.selenium.elements.api_interact.find_element_by import By
from Test.jdi_uitests_webtests.main.page_objects.pages import *
from Test.jdi_uitests_webtests.main.page_objects.sections.footer import Footer


class EpamJDISite(WebSite):

#pages
    home_page = HomePage(url="/index.htm", title="Index Page")
    metals_colors_page = MetalColorPage(url="/page2.htm", title="Metal and Colors")
    contact_form_page = ContactFormPage(url="/page1.htm", title="Contact Form")
    support_page = SupportPage(url="/page3.htm", title="Support")
    dates_page = DatesPage(url="/page4.htm", title="Simple Table")

#elements
    actions_log = TextList(By.css(".logs li"))

#sections
    footer = Footer(By.css(".footer-content"))
    login_page = Login(By.css(".uui-profile-menu"))
