Feature: Response status check

  Scenario: Server error status request
    Given I init service
    When I do status request with 503 code
    Then Response status code equals 503
    And Response status type is SERVER_ERROR
    And Response body is empty