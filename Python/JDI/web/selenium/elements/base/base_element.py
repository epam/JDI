from JDI.web.selenium.elements.api_interact.get_element_module import GetElementModule


class BaseElement(object):

    avatar = None
    parent = None

    if not avatar:
        avatar = GetElementModule()

    def get_driver(self):
        return self.avatar.get_driver()
