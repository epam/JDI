import time

def waiter_decorator(method_to_decorate):
    def waiter(self, *args):
        for i in range(0, 20):
            if method_to_decorate(self, *args):
                return True
            time.sleep(0.1)
    return waiter
