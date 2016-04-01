Feature: base tests

  Scenario: text area base
    Given I'm open "contactFormPage"
    When I'm input to "description" lines "line1\r\nline2\r\nline"
    And I'm input to "description" new line "line last"
    Then Field "description" contains "line1\r\nline2\r\nline3\r\nline last"

  Scenario: text area new input
    Given I'm open "contactFormPage"
    When I'm input to "description" lines "line1\r\nline2\r\nline"
    And I'm input to "description" new input "line last"
    Then Field "description" contains "line last"