Feature: page

  Scenario: page base
    Given I'm open "homePage"
    And I'm go back
    And I'm go forward
    Then I'm on "homePage"
    And Check page url match
    And Check page url contains

  Scenario: page back
    Given I'm open "homePage"
    And I'm open "contactFormPage"
    And I'm go back
    Then I'm on "homePage"

  Scenario: Page forward
    Given I'm open "homePage"
    And I'm open "contactFormPage"
    And I'm go back
    And I'm go forward
    Then I'm on "contactFormPage"

  Scenario: page refresh
    Given I'm open "contactFormPage"
    And I'm fill field "name" by text "John"
    And I'm refresh page
    Then Log is empty