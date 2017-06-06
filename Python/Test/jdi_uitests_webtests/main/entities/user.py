class User(object):

    @staticmethod
    def default():
        return User()

    def __init__(self):
        self.login = "epam"
        self.password = "1234"
        self.name = "Name"
        self.last_name = "Last Name"
        self.description = "Description"
