import time


class Time:

    @staticmethod
    def wait_result_by_conditions(func, func_condition, seconds = 10):
        timer = 0
        step = 0.2
        count = int(seconds / step)
        els = func()
        while not func_condition(els) and timer < count:
            time.sleep(step)
            els = func()
        return els