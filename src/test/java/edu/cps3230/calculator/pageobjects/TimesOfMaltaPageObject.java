package edu.cps3230.calculator.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TimesOfMaltaPageObject
{
    private WebDriver driver;

    public TimesOfMaltaPageObject(WebDriver webDriver)
    {
        this.driver = webDriver;
    }

    public void clickSection(String sectionName)
    {
        // Find the button that takes us to the section with section name, and then click it
        WebElement sectionButton = driver.findElement(By.linkText(sectionName));
        sectionButton.click();
    }

    public String getSectionTitle()
    {
        // First get the container of the section header, then get the header within it and return its text
        WebElement sectionHeaderContainer = driver.findElement(By.className("li-ListingArticles_head"));
        WebElement sectionHeader = sectionHeaderContainer.findElement(By.tagName("a"));
        return sectionHeader.getText();
    }

    public List<WebElement> getStories()
    {
        // Get all story elements on the page
        return driver.findElements(By.className("li-ListingArticles_sub"));
    }
}
