Feature: form

  Scenario: form base
    Given I open "contactFormPage"
    And I fill form "contactForm" data "{name: 'John1', lastName: 'Doe1', description: 'my deck'}"
    Then form "contactForm" contains data "{name: 'John1', lastName: 'Doe1', description: 'my deck'}"

  Scenario: form submit
    Given I open "contactFormPage"
    And I submit form "contactForm" data "{name: 'John1', lastName: 'Doe1', description: 'my deck'}"
#    Then Result contains "Summary: 3\nName: John1\nLast Name: Doe1\nDescription: description"

#  @todo
#  Scenario: wrong form name
#    Given I open "contactFormPage"
#    And I submit form "wrongFormName" data "{name: 'John1', lastName: 'Doe1', description: 'my deck'}"
#  @todo
#  Scenario: wrong data
#    Given I open "contactFormPage"
#    And I submit form "contactForm" data "{wrong1: 'John1', wrong2: 'Doe1', wrong3: 'my deck'}"