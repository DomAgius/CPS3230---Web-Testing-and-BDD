package edu.cps3230.calculator.steps;

import edu.cps3230.calculator.pageobjects.TimesOfMaltaPageObject;
import edu.cps3230.calculator.pageobjects.WeatherCarouselPageObject;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StoriesNavigationSteps
{
    WebDriver driver;
    TimesOfMaltaPageObject times;
    WeatherCarouselPageObject weather;
    // Stores the index of the last current active item in the weather carousel
    int lastActiveIndex;

    @After
    public void teardown()
    {
        // Close browser after each scenario
        driver.close();
    }

    @Given("I am a user of www.timesofmalta.com")
    public void iAmAUserOfWwwTimesofmaltaCom()
    {
        // Create a new instance of the browser
        System.setProperty("webdriver.chrome.driver", "F:/Domenico Agius/Documents/webtesting/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @When("I visit the timesofmalta homepage")
    public void iVisitTheTimesofmaltaHomepage()
    {
        // Go to the home page
        driver.get("https://timesofmalta.com/");
        // Create a new instance of the website's page object
        times = new TimesOfMaltaPageObject(driver);
        // Accept cookies
        times.acceptCookies();
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

    @And("the section should have at least {int} stories")
    public void theSectionShouldHaveAtLeastNumStoriesStories(int numOfStories)
    {
        // Check that we have at least "numOfStories" stories on the page
        Assert.assertTrue(times.getStories().size() >= numOfStories);
    }


    @And("I click on the left carousel button")
    public void iClickOnTheLeftCarouselButton()
    {
        // Get the weather carousel page object
        weather = times.getWeatherCarousel();
        // Close engagement banner which appears after switching pages
        times.closeEngagementBanner();
        // At first click right so that the left carousel button is enabled
        weather.clickRight();
        // Store the current active weather slide index
        lastActiveIndex = weather.getActiveSlideIndex();
        // Then, click the left carousel button
        weather.clickLeft();
    }

    @Then("I should see the previous item")
    public void iShouldSeeThePreviousItem()
    {
        // Check that the previous slide is now marked as active
        Assertions.assertEquals(lastActiveIndex-1, weather.getActiveSlideIndex());
    }

    @And("I click on the right carousel button")
    public void iClickOnTheRightCarouselButton()
    {
        // Get the weather carousel page object
        weather = times.getWeatherCarousel();
        // Close engagement banner which appears after switching pages
        times.closeEngagementBanner();
        // Store the current active weather slide index
        lastActiveIndex = weather.getActiveSlideIndex();
        // Click the right carousel button
        weather.clickRight();
    }

    @Then("I should see the next item")
    public void iShouldSeeTheNextItem()
    {
        // Check that the previous slide is now marked as active
        Assertions.assertEquals(lastActiveIndex+1, weather.getActiveSlideIndex());
    }
}
