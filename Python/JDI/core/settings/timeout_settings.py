class TimeoutSettings(object):

    __wait_element_sec = 20

    def __init__(self):
        self.set_current_timeout_sec(self.__wait_element_sec)

    def set_current_timeout_sec(self, timeout_sec):
        self.__wait_element_sec = timeout_sec

    def get_current_timeout_sec(self):
        return self.__wait_element_sec
