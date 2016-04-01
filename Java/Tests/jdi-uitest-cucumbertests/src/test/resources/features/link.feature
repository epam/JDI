Feature: link

  Scenario: link base
    Given I'm open "homePage"
    Then Link "about" from "footer" contains reference "page3.htm"
    And Link "about" from "footer" much reference ".*htm"
    And Link "about" from "footer" contains "Abou"
    And Link "about" from "footer" much "Abou.*"

  Scenario: link click
    Given I'm open "contactFormPage"
    And I'm click on "about" link from "footer"
    Then Url is "http://ecse00100176.epam.com/page3.htm"