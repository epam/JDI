from JDI.core.settings.jdi_settings import JDISettings
from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


class LinkTests(InitTests):
    link = EpamJDISite.footer.about_link

    def setUp(self):
        super(LinkTests, self).setUp(self.id().split(".")[-1])
        Preconditions.HOME_PAGE.is_in_state()

    def test_click(self):
        self.link.click()
        EpamJDISite.support_page.check_opened()

    def test_get_reference(self):
        Assert.assert_equal(self.link.get_reference(), EpamJDISite.support_page.url)

    def test_wait_reference_test(self):
        Preconditions.SUPPORT_PAGE.is_in_state()
        EpamJDISite.home_page.open()
        Assert.wait_assert_equal(lambda: self.link.get_reference(), EpamJDISite.support_page.url)
