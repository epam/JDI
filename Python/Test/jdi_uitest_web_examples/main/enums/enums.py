from enum import Enum


class HeaderMenu(Enum):
    SOLUTIONS   = "Solution"
    INDUSTRIES  = "Inducstries"
    ABOUT       = "About"
    IDEAS       = "Ideas"
    CAREERS     = "Careers"
    CONTACT     = "Contact"

class HeaderSolutionsMenu(Enum):
    PRODUCT_DEVELOPMENT     = "Product Development"
    ENGINEERING_EXCELLENCE  = "Engineering Excellence"
    CORE_TECHNOLOGIES       = "Core Technologies"
    ASSURANCE               = "Assurance"

class JobCategories(Enum):
    QA ="Software Test Engineering"

class JobListHeaders(Enum):
    NAME     = "name"
    CATEGORY = "category"
    LOCATION = "location"
    APPLY    = "apply"

class Location(Enum):
    SAINT_PETERSBURG = "Russian Federation > Saint-Petersburg"