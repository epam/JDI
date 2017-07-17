class RowColumn:
    num = None
    name = None

    def __init__(self, val):
        if isinstance(val, str):
            self.name = val
        elif isinstance(val, int):
            self.num = val

    def get(self, name_action, num_action):
        return name_action(self.name) if self.has_name() else num_action(self.num)

    def has_name(self):
        return self.name is not None and self.name != ""
