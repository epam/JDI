from pip import logger


class BaseMatcher:
    FOUND = "FOUND"
    is_list_check = False

    def __init__(self): pass

    def is_true(self, condition):
        self._is_true(condition, None)

    def _is_true(self, condition, fail_message):
        self.assert_action("Check that condition '{0}' is True".format(condition), condition, True, fail_message)

    def assert_action(self, log_message, condition, expected_condition, fail_message):
        logger.info(log_message)
        if condition != expected_condition:
            logger.exception(fail_message)
            raise Exception(fail_message)


