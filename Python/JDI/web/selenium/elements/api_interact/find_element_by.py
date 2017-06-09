from selenium.webdriver.common.by import By as Selenium_By


class By(object):

    @staticmethod
    def id(by_id):
        return Selenium_By.ID, by_id

    @staticmethod
    def css(by_css):
        return Selenium_By.CSS_SELECTOR, by_css

    @staticmethod
    def xpath(by_xpath):
        return Selenium_By.XPATH, by_xpath

    @staticmethod
    def link_text(link_text):
        return Selenium_By.LINK_TEXT, link_text
