from JDI.core.settings.jdi_settings import JDISettings
from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.enums.preconditions import Preconditions
from Test.jdi_uitests_webtests.main.page_objects.epam_jdi_site import EpamJDISite
from Test.jdi_uitests_webtests.test.init_tests import InitTests


def check_page_opened(num):
    Assert.is_true(JDISettings.get_driver_factory().get_driver().current_url.endswith("/page{0}.htm".format(num)))


class PaginationTests(InitTests):
    pagination = EpamJDISite.simple_table_page.pagination

    def setUp(self):
        Preconditions.SIMPLE_TABLE_PAGE.is_in_state()

    def test_next(self):
        self.pagination.next()
        check_page_opened(7)

    def test_previous(self):
        self.pagination.previous()
        check_page_opened(5)

    def test_first(self):
        self.pagination.first()
        check_page_opened(1)

    def test_last(self):
        self.pagination.last()
        check_page_opened(2)