from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.button import Button
from JDI.web.selenium.elements.composite.section import Section


class FrameSection(Section):
    label_button = Button(By.css("button"))