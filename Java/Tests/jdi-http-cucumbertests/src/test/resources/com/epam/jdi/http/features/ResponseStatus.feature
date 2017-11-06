Feature: Response status

  Scenario: Performance after load
    Given I init service
    When I call status method with 503 code
    Then Response status code equals 503
    And Response status type is SERVER_ERROR
    And Response body is empty