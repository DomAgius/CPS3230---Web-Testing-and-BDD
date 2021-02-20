package edu.cps3230.calculator.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

    public void acceptCookies()
    {
        // Find and click the accept cookies button
        driver.findElement(By.className("fo-FormCtrl_Btn-Cntr")).click();
    }

    public void closeEngagementBanner()
    {
        // Wait for engagement banner to appear
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("en-EngagementBanner_Close")));
        // Find and click the close button of the engagement banner
        driver.findElement(By.className("en-EngagementBanner_Close")).click();
        // Refresh page to ensure that engagement banner is gone
        driver.navigate().refresh();
    }

    public void clickSection(String sectionName)
    {
        // Find the button that takes us to the section with section name, and then click it
        WebElement sectionButton = driver.findElement(By.linkText(sectionName));
        sectionButton.click();
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

    public List<WebElement> getStories()
    {
        // Get all story elements on the page
        return driver.findElements(By.className("li-ListingArticles_sub"));
    }

    public WeatherCarouselPageObject getWeatherCarousel()
    {
        // Create a new weather carousel page object
        return new WeatherCarouselPageObject(driver);
    }
}
