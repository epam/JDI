import time

def waiter_decorator(method_to_decorate, time_to_wait):
    def waiter(self, reg_exp):
        for i in range(0, time_to_wait):
            if method_to_decorate(self, reg_exp):
                return True
            time.sleep(0.1)
    return waiter
