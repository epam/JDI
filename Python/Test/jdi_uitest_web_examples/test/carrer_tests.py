import unittest

import time

from JDI.web.selenium.elements.composite.web_site import WebSite
from JDI.web.selenium.settings.web_settings import WebSettings
from Test.jdi_uitest_web_examples.main.entities.entities import Attendee
from Test.jdi_uitest_web_examples.main.enums.enums import HeaderMenu
from Test.jdi_uitest_web_examples.main.site.epam.epam_site import EpamSite


class CareerTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        WebSite.init(EpamSite, "chrome")
        WebSettings.logger.info("\nRun Tests from '%s' file" % cls.__name__)

    def setUp(self):
        EpamSite.home_page.should_be_opened()

    def test_send_cv_test(self):
        attendee = Attendee()
        EpamSite.multiple_header_menu.hover_and_click("SOLUTIONS|Product Development")
        EpamSite.product_development_page.check_opened()
        EpamSite.header_menu.select(HeaderMenu.CAREERS)

        EpamSite.career_page.check_opened()
        EpamSite.career_page.job_filter.search(attendee.filter)

        EpamSite.job_listing_page.check_opened()
        # # new Check("Table is not empty").isFalse(jobListingPage.jobsList::isEmpty);
        #
        EpamSite.job_listing_page.get_job_row_by_name("QA Specialist")
        # EpamSite.job_description_page.add_cv_form.submit_form(attendee)
        # # Check("Captcha class contains 'form-field-error'").contains(() -> jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");

    @classmethod
    def tearDownClass(cls):
        try:
            WebSettings.quit_browser()
        except:
            pass
