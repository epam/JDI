Feature: Request headers check

  Scenario: Pass headers and check response
    Given I init service
    When I have the following headers:
      | Name       | Katarina |
      | Id         | 1        |
    And I do get request
    And I print response
    Then Response status type is OK
    And Response "headers.Name" is "Katarina"
    And Response "headers.Id" is "1"