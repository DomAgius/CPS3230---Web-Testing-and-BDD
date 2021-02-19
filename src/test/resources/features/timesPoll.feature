Feature: Times Poll

  In order to give my opinion on certain topics
  As a user of www.timesofmalta.com
  I want to be able to vote in a poll

  Scenario: Click carousel left
    Given I am a user of www.timesofmalta.com
    When I visit the timesofmalta homepage
    And I click on the "Weather" section
    And I click on the left carousel button
    Then I should see the previous item

  Scenario: Click carousel right
    Given I am a user of www.timesofmalta.com
    When I visit the timesofmalta homepage
    And I click on the "Weather" section
    And I click on the left carousel button
    Then I should see the previous item