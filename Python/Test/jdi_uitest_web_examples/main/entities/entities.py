from Test.jdi_uitest_web_examples.main.enums.enums import JobCategories, Locations


class JobSearchFilter(object):

    def __init__(self):
        self.keyword = "QA"
        self.category = JobCategories.QA
        self.location = Locations.MOSCOW


class Attendee(object):

    def __init__(self):
        self.filter = JobSearchFilter()
        self.first_name = "Roman"
        self.last_name = "Iovlev"
        self.email = "roman_iovlev@epam.com"
        self.country = "Russian Federation"
        self.city = "Moscow"
        self.ca = ""
        self.comment = "I WANT TO WORK IN EPAM!!!"

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


