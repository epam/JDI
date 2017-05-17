from selenium.webdriver.common.by import By as Selenium_By


class By(object):

    @staticmethod
    def id(by_id):
        return Selenium_By.ID, by_id

    @staticmethod
    def css(by_css):
        return Selenium_By.CSS_SELECTOR, by_css
