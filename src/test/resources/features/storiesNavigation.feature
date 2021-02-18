Feature: Stories Navigation

  In order to find stories I am interested in
  As a user of www.timesofmalta.com
  I want to be able to view different stories

  Scenario Outline: Reachability of sections
    Given I am a user of www.timesofmalta.com
    When I visit the timesofmalta homepage
    And I click on the <sectionName> section
    Then I should be taken to <sectionName> section
    And the section should have at least <numStories> stories

    Examples:
    |sectionName  |numStories |
    |"Latest"     |10         |
    |"National"   |10         |
    |"World"      |10         |
    |"Opinion"    |10         |
    |"Community"  |10         |
