
class WebDriverByUtils:

    @staticmethod
    def contains_root(by):
        return by is not None and str(by).__contains__(": *root*")

    @staticmethod
    def trim_root(by):
        by[1] = by[1].replace("*root*", "").strip()
        return by
