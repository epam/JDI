Feature: search

  Scenario: search base
    Given I'm open "homePage"
    And I'm find "find string"
    Then I'm on "supportPage"