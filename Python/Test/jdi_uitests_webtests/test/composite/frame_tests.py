import time

from JDI.jdi_assert.testing.assertion import Assert
from Test.jdi_uitests_webtests.main.page_objects.w3c_site.w3c_site import W3cSite
from Test.jdi_uitests_webtests.test.init_tests import W3CInit


class FrameTests(W3CInit):

    @staticmethod
    def test_fill():
        W3cSite.frame_page.try_it_button.click()
        Assert.assert_equal(W3cSite.frame_page.i_frame.label_button.get_text(), "Click Me!")
