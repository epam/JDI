from JDI.web.selenium.elements.composite.web_page import WebPage
from Test.jdi_uitests_webtests.main.page_objects.sections.jdi_paginator import JDIPagination


class SimpleTablePage(WebPage):
    pagination = JDIPagination()
