Feature: Menu item select and text input

  Background: prepare driver
    Given Prepare environment

  Scenario: Navigation and input
    Given I am on Home page
    When I select CAREERS tab
    And I input TestAutomation
    Then Career page is opened
    And TestAutomation is typed
    And Closing driver