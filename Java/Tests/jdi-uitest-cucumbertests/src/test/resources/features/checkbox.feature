Feature: checkbox

  Scenario: checkbox base
    Given I open "metalsColorsPage"
    And I check "cbWater"
    Then checkbox "cbWater" is checked

  Scenario: checkbox base
    Given I open "metalsColorsPage"
    And I click on "cbWater"
    Then checkbox "cbWater" is checked

  Scenario: checkbox base
    Given I open "metalsColorsPage"
    And I check "cbWater"
    And I uncheck "cbWater"
    Then checkbox "cbWater" is unchecked