from enum import Enum


class LogLevels(Enum):

    # OFF = -1,     # no logging
    FATAL = 50,    # unexpected errors
    ERROR = 40,    # critical errors
    WARNING = 30,  # errors due to wrong params
    INFO = 20,     # actions info
    DEBUG = 10,    # debug info (not for prod)
    # TRACE = 8,    # trace info (not for prod)
    # ALL = 100     # all log messages

    __priority = None

    def __init__(self, priority):
        self.__priority = priority

    def get_priority(self):
        return self.__priority

    def equal_or_less_than(self, level):
        return self.get_priority() >= level.get_priority()
