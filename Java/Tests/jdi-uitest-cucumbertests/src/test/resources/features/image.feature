Feature: image
  D:/temp/euro_b.png

  Scenario: image base
    Given I open "contactFormPage"
    And I click on "logoImage"
    Then I'm on "homePage"

  Scenario: image source
    Given I open "contactFormPage"
    Then image "logoImage" has source "https://jdi-framework.github.io/tests/images/Logo_Epam_Color.svg"

  Scenario: image tooltip
    Given I open "contactFormPage"
    Then image "logoImage" has alt ""