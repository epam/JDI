Feature: GET and POST check

  Scenario Outline: Check response
    Given I init service
    When I do <method> request
    Then Response status type is <responseStatus>
    And Response body has values
      | url         | <url>         |
      | headers.Host| httpbin.org |

  Examples:
    | method | responseStatus | url                     |
    | get    | OK             | http://httpbin.org/get  |
    | post   | OK             | http://httpbin.org/post |