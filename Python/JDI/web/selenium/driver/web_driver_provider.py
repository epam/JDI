import os


class WebDriverProvider(object):

    FOLDER_PATH = os.path.dirname(os.path.abspath("")) + "/resources/driver"

    @staticmethod
    def get_chrome_driver_path(folder_path):
        return folder_path + "/chromedriver.exe"

