import tempfile, os

from selenium.webdriver.common.by import By

from JDI.core.settings.jdi_settings import JDISettings
from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite


class CommonActionsData:

    @staticmethod
    def no_elements_message():
        return "No elements selected. Override getSelectedAction or place locator to <select> tag"

    _path = None
    _name = None

    @staticmethod
    def get_file_name():
        if CommonActionsData._path is None:
            CommonActionsData.create_file()
        return CommonActionsData._name

    @staticmethod
    def get_file_path():
        if CommonActionsData._path is None:
            CommonActionsData.create_file()
        return CommonActionsData._path

    @staticmethod
    def create_file():
        dir_name = "c:/temp"
        if not os.path.exists(dir_name):
            os.makedirs(dir_name)

        temp = tempfile.TemporaryFile(dir=dir_name, delete=False)
        temp.write(bytes("mystring", 'utf-8'))
        CommonActionsData._path = temp.name
        CommonActionsData._name = temp.name.split("\\")[-1]

    @staticmethod
    def check_action(text, line_number=0):
        Assert.assert_contains(EpamJDISite.actions_log.get_text_by_line(line_number), text)

    @staticmethod
    def loose_focus():
        JDISettings.get_driver_factory().get_driver().find_element(By.CLASS_NAME,"footer-content").click()

    @staticmethod
    def check_action_throw_error(action, message):
        try:
            action()
        except Exception as ex:
            Assert.assert_contains(str(ex), message)
            return
        except:
            d = 8

        raise Exception("Exception not thrown")


