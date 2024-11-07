package com.ali.qa.pageobjects;

import com.ali.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By removeButton = By.xpath("(//button[contains(@class, 'cart-product-name-ope-trashCan')])[1]"); // Adjust as needed
    private By checkoutButton = By.id("//button[@class='comet-v2-btn comet-v2-btn-primary comet-v2-btn-large comet-v2-btn-block cart-summary-button comet-v2-btn-important']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void removeFirstItem() {
        driver.findElement(removeButton).click();
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
