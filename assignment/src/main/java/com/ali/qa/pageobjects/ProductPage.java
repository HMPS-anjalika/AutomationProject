package com.ali.qa.pageobjects;

import com.ali.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    private By minPriceInput = By.name("minPrice");
    private By maxPriceInput = By.name("maxPrice");
    private By priceFilterButton = By.xpath("//span[@class='price--ok--30GSiFy']");
    private By freeShippingCheckbox = By.xpath("//body/div[@id='root']/div[@class='root--container--2gVZ5S0 root--newRoot--2-6FirH search-root-cls']/div[@class='main2023--container--1Km9a-K main2023--ltr--2Ro1nU3']/div[@class='refine2023--refine--3SE-006']/div[@class='collSelect--collSelect--2qpPgef']/div[@class='collSelect--content--35jau6_']/span[@class='normalItem--listContainer--2ITFweA']/span[2]/span[1]");
    private By products = By.xpath("//*[@id=\"card-list\"]"); // General product locator

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void applyPriceFilter(String minPrice, String maxPrice) {
        driver.findElement(minPriceInput).sendKeys(minPrice);
        driver.findElement(maxPriceInput).sendKeys(maxPrice);
        driver.findElement(priceFilterButton).click();
    }

    public void selectFreeShipping() {
        driver.findElement(freeShippingCheckbox).click();
    }

    public void selectProduct(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElements(products).get(index).click();
    }
}
