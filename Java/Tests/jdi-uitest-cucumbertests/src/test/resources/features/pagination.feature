Feature: pagination

  Scenario: pagination base
    Given I'm open "simpleTablePage"
    And I'm use pagination "pagination" to go next
    And I'm use pagination "pagination" to go previous
    And I'm use pagination "pagination" to select 3
    And I'm use pagination "pagination" to go last
    And I'm use pagination "pagination" to go first