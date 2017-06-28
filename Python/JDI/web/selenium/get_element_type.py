from JDI.web.selenium.elements.api_interact.get_element_module import GetElementModule


class GetElementType:
    locator = None
    parent = None

    def __init__(self, locator, parent):
        self.locator = locator
        self.parent = parent

    def get(self, class_name):
        try:
            if self.locator is None:
                return None
            else:
                result = class_name()
                result.init(self.parent, GetElementModule(self.locator, self.parent))
                return result
        except:
            return None
