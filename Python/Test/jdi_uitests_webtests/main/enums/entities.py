from enum import Enum


class Nature(Enum):
    WATER = "Water"
    EARTH = "Earth"
    WIND  = "Wind"
    FIRE  = "Fire"


class Metals(Enum):
    COL    = "Col"
    GOLD   = "Gold"
    SILVER = "Silver"
    BRONZE = "Bronze"
    SELEN  = "Selen"


class Colors(Enum):
    COLORS = "Colors"
    RED    = "Red"
    GREEN  = "Green"
    BLUE   = "Blue"
    YELLOW = "Yellow"

class Odds(Enum):
    ONE   = "1"
    THREE = "3"
    FIVE  = "5"
    SEVEN = "7"

class Buttons(Enum):
    SUBMIT    = "submit"
    CALCULATE = "calculate"