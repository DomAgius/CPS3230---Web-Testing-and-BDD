Feature: Weather Forecast Carousel

  In order to view the forecasts for the next 5 days
  As a user of www.timesofmalta.com
  I want to be able to navigate a carousel of weather forecasts

  Scenario: Weather carousel exists
    Given I am a user of www.timesofmalta.com
    When I visit the timesofmalta homepage
    And I click on the "Weather" section
    Then I should see a carousel containing weather forecasts

  Scenario: Click carousel right
    Given I am a user of www.timesofmalta.com
    When I visit the timesofmalta homepage
    And I click on the "Weather" section
    And I click on the right carousel button
    Then I should see the next forecast

  Scenario: Click carousel left
    Given I am a user of www.timesofmalta.com
    When I visit the timesofmalta homepage
    And I click on the "Weather" section
    And I click on the left carousel button
    Then I should see the previous forecast