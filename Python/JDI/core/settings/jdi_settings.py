import os
from Test.jdi_uitests_webtests import PROJECT_PATH


class JDISettings(object):

    JDI_SETTINGS_FILE_PATH = "../../jdi.properties"

    __wait_element_sec = 20
    _driver_factory = None
    __logger = None
    _jdi_settings = None

    @staticmethod
    def get_driver_factory(): return JDISettings._driver_factory

    @staticmethod
    def _read_jdi_settings():
        if JDISettings._jdi_settings is None:
            f = open(JDISettings.JDI_SETTINGS_FILE_PATH)
            JDISettings._jdi_settings = dict()
            for line in f:
                param = line.split("=")
                JDISettings._jdi_settings[param[0]] = param[1].strip()

    @staticmethod
    def get_driver_path(): return JDISettings.get_setting_by_name("drivers_folder")

    @staticmethod
    def get_setting_by_name(setting_name):
        if JDISettings._jdi_settings is None or setting_name in JDISettings._jdi_settings:
            JDISettings._read_jdi_settings()
        return JDISettings._jdi_settings[setting_name] if setting_name in JDISettings._jdi_settings else None

    @staticmethod
    def get_current_timeout_sec():
        prop = JDISettings.get_setting_by_name("timeout_wait_element")
        return JDISettings.__wait_element_sec if prop is None else prop

    @staticmethod
    def get_domain():
       return JDISettings.get_setting_by_name("domain")
