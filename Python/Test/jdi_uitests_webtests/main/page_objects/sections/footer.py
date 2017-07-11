from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.link import Link
from JDI.web.selenium.elements.composite.section import Section


class Footer(Section):
    about_link = Link(By.link_text("About"))