Feature: image

  Scenario: image base
    Given I'm open "contactFormPage"
    And I'm click on "logoImage"
    Then I'm on "homePage"

  Scenario: image source
    Given I'm open "contactFormPage"
    Then Image "logoImage" source is "qwe"

  Scenario: image tooltip
    Given I'm open "contactFormPage"
    Then Image "logoImage" alt is "qwe"