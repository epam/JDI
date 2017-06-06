import logging

from JDI.core.logger.log_levels import LogLevels


class JDILogger(object):

    def __init__(self, name="JDI Logger"):
        self.logger = logging.getLogger(name)
        self.__basic_settings()

    log_level = LogLevels.INFO

    def info(self, log_msg):
        if self.log_level.equal_or_less_than(LogLevels.INFO):
            self.logger.info(log_msg)

    def __basic_settings(self):
        # TODO: there is a problem, setLevel takes only int type
        self.logger.setLevel(LogLevels.INFO.value[0])
        self.logger.setLevel(LogLevels.DEBUG.value[0])
        hdlr = logging.FileHandler('jdi.log')
        hdlr.setFormatter(logging.Formatter('%(asctime)s %(levelname)s %(message)s'))
        self.logger.addHandler(hdlr)

