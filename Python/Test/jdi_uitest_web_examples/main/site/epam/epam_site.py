from JDI.core.interfaces.check_page_types import CheckPageTypes
from JDI.web.selenium.elements.api_interact.find_element_by import By
from JDI.web.selenium.elements.common.button import Button
from JDI.web.selenium.elements.complex.menu import Menu
from JDI.web.selenium.elements.composite.web_site import WebSite
from Test.jdi_uitest_web_examples.main.enums.enums import HeaderMenu, HeaderSolutionsMenu
from Test.jdi_uitest_web_examples.main.site.epam.pages import HomePage, CareerPage, ProductDevelopmentPage, \
    JobListingPage, JobDescriptionPage


class EpamSite(WebSite):
    home_page = HomePage(url="/", title="EPAM | Software Product Development Services")
    career_page = CareerPage(url="/careers", title="Careers")
    product_development_page = ProductDevelopmentPage(url="/solutions/core-engineering/product-development")
    job_listing_page = JobListingPage(
         url="/careers/job-listings?sort=best_match&query=Engineer&department=Software+Test+Engineering&city=St-Petersburg&country=Russia",
         url_template="/careers/job-listings", title="Job Listings",
         url_check_type=CheckPageTypes.CONTAINS, title_check_type=CheckPageTypes.CONTAINS)
    job_description_page = None #JobDescriptionPage(
        # url="/careers/job-listings/job.11584#apply", urlTemplate=".*/careers/job-listings/job\\.\\d*#apply",
        # urlCheckType=CheckPageTypes.MATCH)

    header_menu = Menu(by_all_options_names_locator=By.css(".tile-menu>li>a"), parametrized_class=HeaderMenu)
    multiple_header_menu = Menu(by_menu_levels_locators=[By.css(".tile-menu>li>a"),
                                                         By.xpath("//*[@class='tile-menu']//*[@href='/solutions']//..//li")])

    line_menu = None#JDIElements(By.css(".tile-menu>li>a"), Button)

    header_solutions_menu = None #Menu(By.css(".tile-menu .submenu a"), HeaderSolutionsMenu)

