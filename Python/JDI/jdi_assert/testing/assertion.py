import time
from nose.tools import assert_true

from JDI.core.settings.jdi_settings import log
from JDI.web.selenium.settings.WebSettings import WebSettings


class Assert:

    @staticmethod
    def assert_element_test(el, expected_text):
        actual_text = el.get_text()
        assert_true(actual_text == expected_text, "Expected text is '{0}' , but was '{1}'".format(expected_text, actual_text))

    @staticmethod
    def assert_contains(actual_text, expected_text):
        assert_true(actual_text.find(expected_text) is not -1,
                    "Text '{0}' was not found in line '{1}'".format(expected_text, actual_text))

    @staticmethod
    def assert_equal(actual_text, expected_text):
        assert_true(actual_text == expected_text,
                    "Text '{0}' does not equal '{1}'".format(expected_text, actual_text))

    @staticmethod
    def wait_assert_equal(function_actual_text, expected_text, seconds=5):
        actual_text = ""
        t = 0
        timeout = 0.5
        while t < seconds/timeout:
            actual_text = function_actual_text()
            if actual_text == expected_text:
                log.to_do_info_logging = True
                return
            else:
                log.to_do_info_logging = False
                time.sleep(timeout)
                t += timeout
        log.to_do_info_logging = True
        WebSettings.logger.error("Text '{0}' does not equal '{1}'".format(expected_text, actual_text))
        raise AssertionError("Text '{0}' does not equal '{1}'".format(expected_text, actual_text))

    @staticmethod
    def is_false(condition):
        assert_true(not condition)

    @staticmethod
    def is_true(condition, text=None):
        assert_true(condition, text)

    @staticmethod
    def check_text(actual, expected):
        assert actual == expected
