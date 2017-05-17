import logging

from JDI.core.settings.timeout_settings import TimeoutSettings


class JDISettings(object):

    timeouts = TimeoutSettings()
    logger = logging
    _driver_factory = None

    @staticmethod
    def get_driver_factory():
        return JDISettings._driver_factory




