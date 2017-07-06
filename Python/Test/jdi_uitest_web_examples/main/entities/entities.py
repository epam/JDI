from Test.jdi_uitest_web_examples.main.enums.enums import JobCategories, Locations


class JobSearchFilter:

    def __init__(self): pass
    keywords = "QA"
    category = JobCategories.QA
    location = Locations.SAINT_PETERSBURG


class Attendee:
    filter = JobSearchFilter()

    name = "Roman"
    last_name = "Iovlev"
    email = "roman_iovlev@epam.com"
    country = "Russian Federation"
    city = "Saint-Petersburg"
    ca = ""
    comment = "I WANT TO WORK IN EPAM!!!"

    def __str__(self):
        return self.name + " " + self.last_name


class Job:
    def __init__(self, name, category, location):
        self.name = name
        self.category = category
        self.location = location

class User:
    DEFAULT = {"UserTest", "Test Password"}

    name = None
    password = None


