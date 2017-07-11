from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.button import Button
from JDI.web.selenium.elements.composite.web_page import WebPage
from Test.jdi_uitests_webtests.main.page_objects.w3c_site.frame_section import FrameSection


class FramePage(WebPage):
    def __init__(self, url, title=None, domain=None):
        super(FramePage, self).__init__(url, title, domain)

    i_frame = FrameSection(By.id("iframeResult"))

    try_it_button = Button(By.link_text("Try it Yourself »"))