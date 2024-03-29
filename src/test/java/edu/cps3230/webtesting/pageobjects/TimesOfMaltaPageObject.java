package edu.cps3230.webtesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TimesOfMaltaPageObject
{
    private WebDriver driver;

    public TimesOfMaltaPageObject(WebDriver webDriver)
    {
        this.driver = webDriver;
    }

    public void disableEngagementBanner()
    {
        // Wait for accept cookies button to appear
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fo-FormCtrl_Btn-Cntr")));
        // Find and click the accept cookies button
        driver.findElement(By.className("fo-FormCtrl_Btn-Cntr")).click();
        // Refresh page and wait for donation banner to appear
        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("en-EngagementBanner_Close")));
        // Find and click the close button of the donation banner
        driver.findElement(By.className("en-EngagementBanner_Close")).click();
        // Refresh page to ensure that donation banner is gone
        driver.navigate().refresh();
    }

    public void clickSection(String sectionName)
    {
        // Find the button that takes us to the section with name "sectionName" to appear
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(sectionName)));
        // Then click the button
        driver.findElement(By.linkText(sectionName)).click();
    }

    public String getSectionTitle()
    {
        // Wait for section header container to appear
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("li-ListingArticles_head")));
        // Then get the header within it and return its text
        WebElement sectionHeaderContainer = driver.findElement(By.className("li-ListingArticles_head"));
        WebElement sectionHeader = sectionHeaderContainer.findElement(By.tagName("a"));
        return sectionHeader.getText();
    }

    public void searchForArticles(String searchString)
    {
        // Wait for search button to appear and click it
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("na-SecondaryNavigation_SearchBtn")));
        driver.findElement(By.className("na-SecondaryNavigation_SearchBtn")).click();
        // Find search bar and input search string
        WebElement searchbar = driver.findElement(By.className("na-Navigation_SearchBar"));
        searchbar.sendKeys(searchString);
        // Start search by pressing enter
        searchbar.sendKeys(Keys.RETURN);
    }

    public List<WebElement> getStories()
    {
        // Get all story elements on the page
        return driver.findElements(By.className("li-ListingArticles_sub"));
    }

    public String getStoryName(WebElement story)
    {
        // Given a story from a list of stories, find the title element and return its text
        return story.findElement(By.className("wi-WidgetSubCompType_12-title")).getText();
    }

    public String getStoryName()
    {
        // Find the header element, then return the text inside it
        return driver.findElement(By.className("wi-WidgetSubCompType_13-title")).getText();
    }

    public WeatherCarouselPageObject getWeatherCarousel()
    {
        // Create a new weather carousel page object
        return new WeatherCarouselPageObject(driver);
    }
}
