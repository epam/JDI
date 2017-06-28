from JDI.web.selenium.elements.web_cascade_init import WebCascadeInit


class CascadeInit:
    @staticmethod
    def init_elements(parent):
        elements = WebCascadeInit.get_all_elements(parent)
        for el_name in elements:
            el = getattr(parent, el_name)
            if WebCascadeInit.is_element(el):
                el.name = el_name
                if WebCascadeInit.is_composite(el):
                    WebCascadeInit.init_site_page(el)