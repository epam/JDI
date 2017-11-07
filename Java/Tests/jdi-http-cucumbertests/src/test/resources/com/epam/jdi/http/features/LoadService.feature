Feature: Performance after load check

  Scenario: Load service
    Given I init service
    When I load service for 20000 sec with get requests
    Then I check number of requests
    And I check if performance results contain any fails
    And Average response time is lesser than 2000 sec