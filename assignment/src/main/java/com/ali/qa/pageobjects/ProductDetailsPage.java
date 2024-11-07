package com.ali.qa.pageobjects;

import com.ali.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

    private By addToCartButton = By.xpath("//span[normalize-space()='Add to cart']");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void closeTabAndSwitchBack() {
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }
}
