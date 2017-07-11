from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.image import Image
from JDI.web.selenium.elements.composite.section import Section
from Test.jdi_uitests_webtests.main.page_objects.sections.jdi_search import JDISearch


class Header(Section):
    image = Image(By.xpath('//img[@src="label/Logo_Epam_Color.svg"]'))
    search_section = JDISearch()