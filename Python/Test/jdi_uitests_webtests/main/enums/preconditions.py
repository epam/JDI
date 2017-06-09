from enum import Enum

from selenium.webdriver.chrome.webdriver import WebDriver

from JDI.core.settings.jdi_settings import JDISettings
from JDI.web.selenium.preconditions.web_preconditions import WebPreconditions


class Preconditions(Enum):
    HOME_PAGE = "index.htm",
    CONTACT_PAGE = "page1.htm",
    METALS_AND_COLORS_PAGE = "page2.htm"

    def is_in_state(self):
        if not WebPreconditions.check_url(self.value):
            WebPreconditions.open_uri(JDISettings.get_domain() + self.value)

