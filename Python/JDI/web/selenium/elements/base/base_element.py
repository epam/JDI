from JDI.web.selenium.elements.api_interact.get_element_module import GetElementModule


class BaseElement(object):

    avatar = None
    parent = None

    def __init__(self, by_locator):
        self.avatar = GetElementModule(by_locator)
