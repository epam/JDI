from JDI.web.selenium.elements.composite.web_site import WebSite
from tests.site.yandex.pages.home_page import HomePage


class YandexSite(WebSite):

    domain = "https://ya.ru"

    home_page = HomePage(domain)

    search_page = None
