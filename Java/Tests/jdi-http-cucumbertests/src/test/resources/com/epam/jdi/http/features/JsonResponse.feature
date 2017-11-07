Feature: Json response check

  Scenario: Check json response
    Given I init service
    And I set JSON request content type
    When I do get request
    Then Response status type is OK
    And Response "url" is "http://httpbin.org/get"
    And Response "headers.Host" is "httpbin.org"
    And Response header "Connection" is "keep-alive"
    And Json response "url" is "http://httpbin.org/get"