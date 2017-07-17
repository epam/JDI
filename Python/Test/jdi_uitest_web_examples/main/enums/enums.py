from enum import Enum


class HeaderMenu(Enum):
    SOLUTIONS   = "SOLUTIONS"
    INDUSTRIES  = "INDUSTRIES"
    ABOUT       = "ABOUT"
    IDEAS       = "IDEAS"
    CAREERS     = "CAREERS"
    CONTACT     = "CONTACT"


class HeaderSolutionsMenu(Enum):
    PRODUCT_DEVELOPMENT     = "Product Development"
    ENGINEERING_EXCELLENCE  = "Engineering Excellence"
    CORE_TECHNOLOGIES       = "Core Technologies"
    ASSURANCE               = "Assurance"


class JobCategories(Enum):
    QA = "Software Test Engineering"


class JobListHeaders(Enum):
    NAME     = "name"
    CATEGORY = "category"
    LOCATION = "location"
    APPLY    = "apply"


class Locations(Enum):
    SAINT_PETERSBURG = "Russian Federation > Saint-Petersburg"
    MOSCOW = "Russian Federation > Moscow"