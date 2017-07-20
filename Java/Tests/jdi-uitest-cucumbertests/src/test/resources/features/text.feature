Feature: text
  Scenario: text
    Given I open "homePage"
    Then text "text" contains "ENIM AD MINIM VENIAM, QUIS NOSTRUD"
    And text "text" match ".* IPSUM DOLOR SIT AMET.*"

  Scenario: textField
    Given I open "contactFormPage"
    And I fill field "name" by text "John"
    And I fill field "lastName" by text "Doe"
    Then text "name" contains "John"
    And text "lastName" contains "Doe"