Feature: Login functionality

  Background: prepare driver
    Given Prepare environment

  Scenario Outline: Login function
    Given I am on Home page
    When I login as <userName>/<userPassword>
    Then Login <status>
    And Closing driver

    Examples:
      | userName | userPassword | status    |
      | Roman    | 12345        | failure   |
      | Alexey   | qweqwe       | failure   |
      | epam     | 1234         | succeeded |
      | Sergei   | qweqwe43     | failure   |