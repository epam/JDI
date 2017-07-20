Feature: page

  Scenario: page base
    Given I open "homePage"
    And I go back
    And I go forward
    Then I'm on "homePage"
    And I check that page url match
    And I check that page url contains

  Scenario: page back
    Given I open "homePage"
    And I open "contactFormPage"
    And I go back
    Then I'm on "homePage"

  Scenario: Page forward
    Given I open "homePage"
    And I open "contactFormPage"
    And I go back
    And I go forward
    Then I'm on "contactFormPage"

  Scenario: page refresh
    Given I open "contactFormPage"
    And I fill field "name" by text "John"
    And I refresh page
    Then Log is empty