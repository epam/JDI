import logging
from datetime import datetime
from enum import Enum

from JDI.web.selenium.settings.WebSettings import WebSettings


def scenario(action_name, values_list={}):
    def real_scenario(func):
        def wrapper(*el):
            a2 = action_name
            for value in values_list:
                if value not in {"value_from_function", "2_values_from_function"}:
                    val = getattr(el[0], value)
                    val = val if val is not None else "not named"
                    a2 = str.replace(a2, "%s", val, 1)
                elif value == "value_from_function":
                    a2 = str.replace(a2, "%s", str(el[1]))
                elif value == "2_values_from_function":
                    a2 = str.replace(str.replace(a2, "%s", str(el[1]), 1), "%s", str(el[2]))
            WebSettings.logger.info("{0} '{1}' '{2}' ({3})".format(a2, el[0].__class__.__name__, el[0].name, str(el[0])))
            return func(*el)
        return wrapper
    return real_scenario