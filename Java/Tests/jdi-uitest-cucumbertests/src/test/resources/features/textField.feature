Feature: text field

  Scenario: text field base
    Given I'm open "contactFormPage"
    And I'm input to "name" text "Dima"
    And I'm new input to "lastName" text "Dima"
    And I'm send keys to "lastName" text "Dima"
    And I'm clear "lastName"