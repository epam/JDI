import logging
from datetime import datetime

from JDI.web.selenium.settings.WebSettings import WebSettings


def scenario(action_name, values_list={}):
    def real_scenario(func):
        def wrapper(el):
            a2 = action_name
            for value in values_list:
                a2 = str.replace(a2, "%s", getattr(el, value), 1)
            WebSettings.logger.info("{0} {1} '{2}' ({3})".format(a2, el.__class__.__name__, el.name, str(el)))
            func(el)
        return wrapper
    return real_scenario