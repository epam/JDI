from JDI.web.selenium.elements.api_interact.get_element_module import GetElementModule


class BaseElement(object):
    name = None
    parent = None
    avatar = None

    def __init__(self, by_locator=None):
        self.avatar = GetElementModule(by_locator, self)

    def get_driver(self):
        return self.avatar.get_driver()

    def __str__(self):
        return self.__class__.__name__

    def _get_type_name(self):
        return self.__class__.__name__

    def get_name(self):
        return self.name if self.name is not None else self._get_type_name()

    def get_parent(self):
        return self.parent

    def init(self, parent, avatar):
        from JDI.web.selenium.elements.cascade_init import WebCascadeInit
        WebCascadeInit.init_elements(self)
        self.set_avatar(avatar)
        self.set_parent(parent)
        return self

    def set_avatar(self, avatar):
        self.avatar = avatar
        return self

    def set_parent(self, parent):
        self.parent = parent

    def get_locator(self):
        return self.avatar.by_locator

    def __str__(self):
        str = "Name " + self.__class__.__name__
        if "by_locator" in dir(self.avatar):
            if self.avatar.by_locator is not None:
                str += "; Locator: %s:'%s'" % (self.avatar.by_locator[0], self.avatar.by_locator[1])
        if self.parent is not None:
            if "avatar" in dir(self.parent):
                if self.parent.avatar.by_locator is not None:
                    str += "; Parent: %s:'%s'" % (self.parent.avatar.by_locator[0], self.parent.avatar.by_locator[1])

        return str

    def has_locator(self):
        return self.avatar.has_locator()
