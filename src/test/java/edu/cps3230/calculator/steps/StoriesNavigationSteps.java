package edu.cps3230.calculator.steps;

import edu.cps3230.calculator.pageobjects.TimesOfMaltaPageObject;
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

import java.util.List;

public class StoriesNavigationSteps
{
    WebDriver driver;
    TimesOfMaltaPageObject times;

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

    @After
    public void teardown()
    {
        // Close browser after each scenario
        driver.close();
    }
}