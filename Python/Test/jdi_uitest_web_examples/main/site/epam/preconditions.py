from enum import Enum

from JDI.core.settings.jdi_settings import JDISettings
from JDI.web.selenium.preconditions.web_preconditions import WebPreconditions


class Precondition(Enum):
    HOME = ""

    def is_in_state(self):
        str_value = self.value[0] if isinstance(self.value, tuple) else self.value
        if not WebPreconditions.check_url(str_value):
            WebPreconditions.open_uri(JDISettings.get_domain() + str_value)