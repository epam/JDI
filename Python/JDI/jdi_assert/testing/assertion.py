from nose.tools import assert_true


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
    def is_false(condition):
        assert_true(not condition)

    @staticmethod
    def is_true(condition):
        assert_true(condition)

    @staticmethod
    def check_text(actual, expected):
        assert actual == expected
