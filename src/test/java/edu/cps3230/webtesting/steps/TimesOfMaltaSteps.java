package edu.cps3230.webtesting.steps;

import edu.cps3230.webtesting.pageobjects.TimesOfMaltaPageObject;
import edu.cps3230.webtesting.pageobjects.WeatherCarouselPageObject;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TimesOfMaltaSteps
{
    WebDriver driver;
    TimesOfMaltaPageObject times;
    WeatherCarouselPageObject weather;
    // Stores the index of the last current active forecast in the weather carousel
    int lastActiveIndex;
    // Stores the name of the first story in the search results
    String firstStoryName;

    @After
    public void teardown()
    {
        // Close browser after each scenario
        driver.close();
    }

    @Given("I am a user of www.timesofmalta.com")
    public void iAmAUserOfWwwTimesofmaltaCom()
    {
        // Do nothing - no authentication needs to be performed
    }

    @When("I visit the timesofmalta homepage")
    public void iVisitTheTimesofmaltaHomepage()
    {
        // Set chrome driver path
        Path chromeDriverPath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "drivers",
                "chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",  chromeDriverPath.toString());
        // Create a new instance of the browser
        driver = new ChromeDriver();
        // Go to the home page
        driver.get("https://timesofmalta.com/");
        // Create a new instance of the website's page object
        times = new TimesOfMaltaPageObject(driver);
        // Disable the engagement banner at the bottom of the page
        times.disableEngagementBanner();
    }

    @And("I click on the {string} section")
    public void iClickOnTheSectionNameSection(String sectionName)
    {
        // Click on specific section
        times.clickSection(sectionName);
    }

    @Then("I should be taken to {string} section")
    public void iShouldBeTakenToSectionNameSection(String sectionName)
    {
        // Check that the expected and actual section titles match
        Assertions.assertEquals(sectionName, times.getSectionTitle());
    }

    @And("there should be at least {int} stories")
    public void theSectionShouldHaveAtLeastNumStoriesStories(int numOfStories)
    {
        // Check that we have at least "numOfStories" stories on the page
        Assert.assertTrue(times.getStories().size() >= numOfStories);
    }

    /* Steps specific to search functionality */
    @When("I search for stories about {string}")
    public void iSearchForStoriesAbout(String searchString)
    {
        // Search for articles using the given search string
        times.searchForArticles(searchString);
    }

    @Then("I should see the search results")
    public void iShouldSeeTheSearchResults()
    {
        // Check that the header says "Search Results"
        Assertions.assertEquals("Search Results", times.getSectionTitle());
    }

    @When("I click the first story in the results")
    public void iClickTheFirstStoryInTheResults()
    {
        // Get the first story in the results
        WebElement firstStory = times.getStories().get(0);
        // Store its name
        firstStoryName = times.getStoryName(firstStory);
        // And click on the story
        firstStory.click();
    }

    @Then("I should be taken to the story")
    public void iShouldBeTakenToTheStory()
    {
        // Check that the name of the story in the search results, matches the name on the story page
        Assertions.assertEquals(firstStoryName, times.getStoryName());
    }

    /* Steps specific to the weather carousel */

    @Then("I should see a carousel containing weather forecasts")
    public void iShouldSeeACarouselContainingWeatherForecasts()
    {
        // Get the weather carousel page object
        weather = times.getWeatherCarousel();
        // Check if the carousel's wrapper element exists
        Assertions.assertNotNull(weather.getCarouselWrapper());
    }

    @And("I click on the right carousel button")
    public void iClickOnTheRightCarouselButton()
    {
        // Get the weather carousel page object
        weather = times.getWeatherCarousel();
        // Store the current active forecast tile index
        lastActiveIndex = weather.getActiveTileIndex();
        // Click the right carousel button
        weather.clickRight();
    }

    @Then("I should see the next forecast")
    public void iShouldSeeTheNextForecast()
    {
        // Check that the previous forecast tile is now marked as active
        Assertions.assertEquals(lastActiveIndex+1, weather.getActiveTileIndex());
    }

    @And("I click on the left carousel button")
    public void iClickOnTheLeftCarouselButton()
    {
        // Get the weather carousel page object
        weather = times.getWeatherCarousel();
        // At first click right so that the left carousel button is enabled
        weather.clickRight();
        // Store the current active forecast tile index
        lastActiveIndex = weather.getActiveTileIndex();
        // Then, click the left carousel button
        weather.clickLeft();
    }

    @Then("I should see the previous forecast")
    public void iShouldSeeThePreviousForecast()
    {
        // Check that the previous forecast tile is now marked as active
        Assertions.assertEquals(lastActiveIndex-1, weather.getActiveTileIndex());
    }
}
