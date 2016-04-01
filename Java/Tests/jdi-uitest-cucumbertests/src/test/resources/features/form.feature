Feature: form

  Scenario: form base
    Given I'm open "contactFormPage"
    And I'm fill form "contactForm" data "{name: 'John1', lastName: 'Doe1', description: 'my deck'}"
    Then Form "contactForm" contains data "{name: 'John1', lastName: 'Doe1', description: 'my deck'}"

  Scenario: form submit
    Given I'm open "contactFormPage"
    And I'm submit form "contactForm" data "{name: 'John1', lastName: 'Doe1', description: 'my deck'}"
#    Then Result contains "Summary: 3\nName: John1\nLast Name: Doe1\nDescription: description"

  Scenario: wrong form name
    Given I'm open "contactFormPage"
    And I'm submit form "wrongFormName" data "{name: 'John1', lastName: 'Doe1', description: 'my deck'}"

  Scenario: wrong data
    Given I'm open "contactFormPage"
    And I'm submit form "contactForm" data "{wrong1: 'John1', wrong2: 'Doe1', wrong3: 'my deck'}"