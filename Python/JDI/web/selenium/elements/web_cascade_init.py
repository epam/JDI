from JDI.web.selenium.elements.base.base_element import BaseElement
from JDI.web.selenium.elements.composite.form import Section
from JDI.web.selenium.elements.composite.web_page import WebPage


class WebCascadeInit:
    @staticmethod
    def init_site_page(site):
        elements = WebCascadeInit.get_all_elements(site)
        for el_name in elements:
            if el_name != "parent":
                el = getattr(site, el_name)
                if WebCascadeInit.is_element(el):
                    if str.startswith(el_name, "i_frame"):
                        el.avatar.frame_locator = el.avatar.by_locator
                    el.name = el_name
                    el.parent = site
                    if WebCascadeInit.is_page(el): el.parent = site
                    if WebCascadeInit.is_composite(el) or WebCascadeInit.is_page(el):
                        WebCascadeInit.init_site_page(el)

    @staticmethod
    def get_all_elements(site):
        res = list()
        iterator = filter(lambda arg: not callable(getattr(site, arg)) and not arg.startswith('_'), dir(site))
        while True:
            try:
                res.append(next(iterator))
            except StopIteration:
                return res

    @staticmethod
    def is_page(el):
        return isinstance(el, WebPage)

    @staticmethod
    def is_composite(el):
        return isinstance(el, Section)

    @staticmethod
    def is_element(el):
        return isinstance(el, BaseElement)

    @staticmethod
    def get_element_name(el):
        return type(el).__name__

    @staticmethod
    def init_elements(parent):
        elements = WebCascadeInit.get_all_elements(parent)
        for el_name in elements:
            el = getattr(parent, el_name)
            if WebCascadeInit.is_element(el):
                el.name = el_name
                if WebCascadeInit.is_composite(el):
                    WebCascadeInit.init_site_page(el)
