from selenium.webdriver.common.by import By as Selenium_By


class By(object):

    @staticmethod
    def id(by_id):
        return Selenium_By.ID, by_id
