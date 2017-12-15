Feature: Json response check

  Scenario: Check json response
    Given I init service
    And I set JSON request content type
    When I do get request
    Then Response status type is OK
    And Response body has values
      |url         |http://httpbin.org/get|
      |headers.Host|httpbin.org           |
    And Response header "Connection" is "keep-alive"