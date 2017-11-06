Feature: Call GET and POST

  Scenario Outline: Check response
    Given I init service
    When I call <method> method
    Then Response status type is <responseStatus>
    And Response "url" is "<url>"
    And Response "headers.Host" is "httpbin.org"

  Examples:
    | method | responseStatus | url                     |
    | get    | OK             | http://httpbin.org/get  |
    | post   | OK             | http://httpbin.org/post |