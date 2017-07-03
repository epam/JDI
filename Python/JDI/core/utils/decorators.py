import time as time2

from JDI.core.settings.jdi_settings import log
from JDI.web.selenium.settings.WebSettings import WebSettings


def action_logging_start(action_name, values_list, el):
    if log.to_do_info_logging:
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


def action_logging_stop(action_name):
    if log.to_do_info_logging:
        WebSettings.logger.info("%s correctly done" % action_name)


def scenario(action_name, values_list={}):
    def real_scenario(func):
        def wrapper(*el):
            action_logging_start(action_name, values_list, el)
            t = 0
            done = False
            ex = None
            try:
                while t < 20:
                    try:
                        ex = None
                        done = True
                        return func(*el)
                    except:
                        done = False
                        time2.sleep(0.5)
                        t += 1
                    finally:
                        if done:
                            action_logging_stop(action_name)
            finally:
                if ex is not None and log.to_do_info_logging:
                    WebSettings.logger.error(ex)

        return wrapper

    return real_scenario
