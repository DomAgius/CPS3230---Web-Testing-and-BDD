Feature: Stories Navigation

  In order to find stories I am interested in
  As a user of www.timesofmalta.com
  I want to be able to view different stories

  Scenario Outline: Reachability of sections
    Given I am a user of www.timesofmalta.com
    When I visit the timesofmalta homepage
    And I click on the <sectionName> section
    Then I should be taken to <sectionName> section
    And there should be at least <numStories> stories

    Examples:
    |sectionName  |numStories |
    |"Latest"     |5          |
    |"National"   |8          |
    |"World"      |10         |
    |"Opinion"    |12         |
    |"Community"  |15         |

  Scenario: Search functionality
    Given I am a user of www.timesofmalta.com
    When I visit the timesofmalta homepage
    And I search for stories about "Donald Trump"
    Then I should see the search results
    And there should be at least 5 stories
    When I click the first story in the results
    Then I should be taken to the story
