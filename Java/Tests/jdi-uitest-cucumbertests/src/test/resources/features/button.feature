Feature: button

  Scenario: button base
    Given I open "contactFormPage"
    And I fill field "name" by text "dima"
    And I click on "contactSubmit"
    Then Log contains "15:56:16 submit:button clicked\n15:56:15 Name: value changed to dima"
  @test
  Scenario: button contains and match
    Given I open "metalsColorsPage"
    Then button "СЕКЦИЯ.КНОПКА" contains "CUL"
    And button "calculateButton" match ".*LCU.*"