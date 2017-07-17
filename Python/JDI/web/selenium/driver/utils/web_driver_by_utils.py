class WebDriverByUtils:
    @staticmethod
    def contains_root(by):
        return by is not None and str(by).__contains__(": *root*")

    @staticmethod
    def trim_root(by):
        by[1] = by[1].replace("*root*", "").strip()
        return by

    @staticmethod
    def fill_by_template(by, args):
        by_locator = WebDriverByUtils.get_by_locator(by)
        if "%" not in by_locator:
            raise RuntimeError(WebDriverByUtils.get_bad_locator_msg(by_locator, args))
        try:
            by_locator = by_locator % args
        except Exception as ex:
            raise RuntimeError(ex, WebDriverByUtils.get_bad_locator_msg(by_locator, args))
        return tuple([by[0], by_locator])


    @staticmethod
    def get_by_locator(by):
        return by[1]

    @staticmethod
    def get_bad_locator_msg(by_locator, args):
        return "Bad locator template '" + by_locator + "'. Args: " + "; ".join(args) + "."
