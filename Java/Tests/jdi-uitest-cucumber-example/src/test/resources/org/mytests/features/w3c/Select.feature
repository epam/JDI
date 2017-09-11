Feature: Dropdown item select functionality

  Background: prepare driver
    Given Prepare environment

  Scenario: Car select
    Given I am on DropDown page
    When I select Saab item
    Then Saab item is selected
    And Closing driver

  Scenario: Car simple select
    Given I am on DropDown page
    When I select Saab item using simple dropdown
    Then Saab item is selected using simple dropdown
    And Closing driver