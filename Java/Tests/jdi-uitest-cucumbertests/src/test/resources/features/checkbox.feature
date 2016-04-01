Feature: checkbox

  Scenario: checkbox base
    Given I'm open "metalsColorsPage"
    And I'm check "cbWater"
    Then Checkbox "cbWater" is checked

  Scenario: checkbox base
    Given I'm open "metalsColorsPage"
    And I'm click on "cbWater"
    Then Checkbox "cbWater" is checked

  Scenario: checkbox base
    Given I'm open "metalsColorsPage"
    And I'm check "cbWater"
    And I'm unchecked "cbWater"
    Then Checkbox "cbWater" is unchecked