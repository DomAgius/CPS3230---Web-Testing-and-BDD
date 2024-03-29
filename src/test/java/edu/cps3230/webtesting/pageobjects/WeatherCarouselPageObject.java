package edu.cps3230.webtesting.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WeatherCarouselPageObject
{
    private WebDriver driver;

    public WeatherCarouselPageObject(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebElement getCarouselWrapper()
    {
        // Search for the carousel wrapper element
        List<WebElement> elements = driver.findElements(By.className("swiper-wrapper"));

        // If list has only one element, return it
        if (elements.size() == 1)
        {
            return elements.get(0);
        }
        // If it has more or less than 1 elements, there is an error so return null
        else
        {
            return null;
        }
    }

    public void clickLeft()
    {
        // Find the left carousel button and click it
        driver.findElement(By.id("js-prev")).click();
    }

    public void clickRight()
    {
        // Find the right carousel button and click it
        driver.findElement(By.id("js-next")).click();
    }

    public int getActiveTileIndex()
    {
        // Wait until one of the weather tiles becomes the active tile (it may not appear immediately)
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("swiper-slide-active")));
        // Get all weather tiles
        List<WebElement> tiles = driver.findElements(By.className("we-Weather_Week_Single"));
        // Find the active weather tile
        WebElement activeTile = driver.findElement(By.className("swiper-slide-active"));
        // Return the index of the active tile in the list
        return tiles.indexOf(activeTile);
    }
}
