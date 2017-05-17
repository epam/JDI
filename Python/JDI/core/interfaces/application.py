class Application(object):

    current_site = None

    __driver_name = None

    def set_driver_name(self, driver_name):
        self.__driver_name = driver_name
