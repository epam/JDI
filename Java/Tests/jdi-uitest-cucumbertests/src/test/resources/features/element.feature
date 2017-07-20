Feature: element

  Scenario: element base
    Given I open "metalsColorsPage"
    And for element "calculateButton" I set attribute "test-name" on "John"
    Then element "calculateButton" has attribute "test-name" with value "John"
    # Has no attr ?