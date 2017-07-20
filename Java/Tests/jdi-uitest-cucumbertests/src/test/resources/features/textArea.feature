Feature: base tests

  Scenario: text area base
    Given I open "contactFormPage"
    When I input to "description" lines "line1\r\nline2\r\nline"
    And I input to "description" new line "line last"
    Then field "description" contains "line1\r\nline2\r\nline3\r\nline last"

  Scenario: text area new input
    Given I open "contactFormPage"
    When I input to "description" lines "line1\r\nline2\r\nline"
    And I input to "description" new input "line last"
    Then field "description" contains "line last"