from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite


class CommonActionData:

    @staticmethod
    def check_action(text):
        Assert.assert_contains(EpamJDISite.actions_log.get_first_text(), text)
