Feature: text
  Scenario: text
    Given I'm open "homePage"
    Then Text "text" contains "ENIM AD MINIM VENIAM, QUIS NOSTRUD"
    And Text "text" mach ".* IPSUM DOLOR SIT AMET.*"

  Scenario: textField
    Given I'm open "contactFormPage"
    And I'm fill field "name" by text "John"
    And I'm fill field "lastName" by text "Doe"
    Then Text "name" contains "John"
    And Text "lastName" contains "Doe"