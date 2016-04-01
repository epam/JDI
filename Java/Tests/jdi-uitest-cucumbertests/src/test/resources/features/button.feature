Feature: button

  Scenario: button base
    Given I'm open "contactFormPage"
    And I'm fill field "name" by text "dima"
    And I'm click on "contactSubmit"
    Then Log contains "15:56:16 submit:button clicked\n15:56:15 Name: value changed to dima"

  Scenario: button contains and match
    Given I'm open "metalsColorsPage"
    Then Button "calculateButton" contains "CUL"
    And Button "calculateButton" mach ".*LCU.*"