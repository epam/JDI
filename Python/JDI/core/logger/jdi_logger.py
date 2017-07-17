import logging

from JDI.core.logger.log_levels import LogLevels


class JDILogger(object):

    def __init__(self, name="JDI Logger"):
        self.logger = logging.getLogger(name)
        self.__basic_settings()

    log_level = {LogLevels.INFO, LogLevels.FATAL, LogLevels.ERROR}

    def info(self, log_msg):
        if LogLevels.INFO in self.log_level:
            self.logger.setLevel(LogLevels.INFO.value[0])
            self.logger.info(log_msg)

    def debug(self, log_msg):
        if LogLevels.DEBUG in self.log_level:
            self.logger.setLevel(LogLevels.DEBUG.value[0])
            self.logger.debug(log_msg)

    def fatal(self, log_msg):
        if LogLevels.FATAL in self.log_level:
            self.logger.setLevel(LogLevels.FATAL.value[0])
            self.logger.fatal(log_msg)

    def warning(self, log_msg):
        if LogLevels.WARNING in self.log_level:
            self.logger.setLevel(LogLevels.WARNING.value[0])
            self.logger.warning(log_msg)

    def error(self, log_msg):
        if LogLevels.ERROR in self.log_level:
            self.logger.setLevel(LogLevels.ERROR.value[0])
            self.logger.error(log_msg)

    def __basic_settings(self):
        hdlr = logging.FileHandler('jdi.log')
        hdlr.setFormatter(logging.Formatter('%(asctime)s %(levelname)s %(message)s'))
        self.logger.addHandler(hdlr)

