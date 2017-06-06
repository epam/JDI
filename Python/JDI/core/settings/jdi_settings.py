from JDI.core.settings.timeout_settings import TimeoutSettings


class JDISettings(object):

    timeouts = TimeoutSettings()
    _driver_factory = None
    __logger = None
    _jdi_settings = None

    @staticmethod
    def get_driver_factory():
        return JDISettings._driver_factory

    @staticmethod
    def read_jdi_settings():
        if JDISettings._jdi_settings is None:
            f = open("../jdi.properties")
            JDISettings._jdi_settings = dict()
            for line in f:
                param = line.split("=")
                JDISettings._jdi_settings[param[0]] = param[1].strip()

    @staticmethod
    def get_driver_path():
        if JDISettings._jdi_settings is None or "drivers_folder" in JDISettings._jdi_settings:
            JDISettings.read_jdi_settings()
        return JDISettings._jdi_settings["drivers_folder"]

    # @staticmethod
    # def get_logger(name="JDI"):
    #     if JDISettings.__logger:
    #         return JDISettings.__logger
    #     else:
    #         return JDISettings.set_logger(name)
    #
    # @staticmethod
    # def set_logger(name):
    #     logger =
    #     logger.setLevel(logging.INFO)
    #     logger.setLevel(logging.DEBUG)
    #     hdlr = logging.FileHandler('jdi.log')
    #     hdlr.setFormatter(logging.Formatter('%(asctime)s %(levelname)s %(message)s'))
    #     logger.addHandler(hdlr)
    #     JDISettings.__logger = logger
    #     return JDISettings.__logger





