import time
from nose.tools import assert_true

from JDI.core.settings.jdi_settings import log
from JDI.web.selenium.settings.WebSettings import WebSettings


class Assert:
    @staticmethod
    def _assert_true(result, assert_name, assert_log):
        if (not callable(result) and not result) or (callable(result) and not result()):
            WebSettings.logger.error(assert_name + ":::" + assert_log + ":::Failed")
            raise AssertionError(assert_name + ":::" + assert_log + ":::Failed")
        else:
            WebSettings.logger.info(assert_name + ":::Success")

    @staticmethod
    def _repeat_while_not_true(bool_function, seconds):
        t = 0
        timeout = 0.5
        while t < seconds / timeout:
            if bool_function():
                log.to_do_info_logging = True
                return True
            else:
                log.to_do_info_logging = False
                time.sleep(timeout)
                t += timeout
        log.to_do_info_logging = True
        return False

    @staticmethod
    def assert_element_test(el, expected_text):
        actual_text = el.get_text()
        Assert._assert_true(lambda: actual_text == expected_text,
                            "Assert element test",
                            "Check that '{0}' equals to '{1}'".format(expected_text, actual_text))

    @staticmethod
    def assert_contains(actual_text, expected_text):
        Assert._assert_true(lambda: actual_text.find(expected_text) is not -1,
                            "Assert element contains text",
                            "Check that '{0}' was found in line '{1}'".format(expected_text, actual_text))

    @staticmethod
    def assert_equal(actual_text, expected_text):
        Assert._assert_true(lambda: actual_text == expected_text,
                            "Assert test",
                            "Check that '{0}' equals '{1}'".format(expected_text, actual_text))

    @staticmethod
    def wait_assert_equal(function_actual_text, expected_text, seconds=5):
        Assert._assert_true(lambda: Assert._repeat_while_not_true(lambda: function_actual_text() == expected_text,
                                                                  seconds),
                            "Wait text equal",
                            "Check text '{0}' equals '{1}' in {2} seconds".format(expected_text, function_actual_text(),
                                                                                  seconds))

    @staticmethod
    def assert_false(condition):
        Assert._assert_true(not condition,
                            "Assert condition is false",
                            "Check that {0} is false".format(condition))

    @staticmethod
    def assert_true(condition):
        Assert._assert_true(condition,
                            "Assert condition is true",
                            "Check that {0} is true".format(condition))
