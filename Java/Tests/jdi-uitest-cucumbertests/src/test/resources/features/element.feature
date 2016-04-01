Feature: element

  Scenario: element base
    Given I'm open "metalsColorsPage"
    And For element "calculateButton" I set attribute "test-name" on "John"
    Then Element "calculateButton" has attribute "test-name" with value "John"
    # Has no attr ?