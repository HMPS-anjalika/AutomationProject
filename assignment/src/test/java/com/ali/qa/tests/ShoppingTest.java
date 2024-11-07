package com.ali.qa.tests;

import com.ali.qa.base.BasePage;
import com.ali.qa.pageobjects.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShoppingTest extends BasePage {

    HomePage homePage;
    ProductPage productPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;

    public ShoppingTest() {
        super(driver);
    }

    @BeforeClass
    public void setUp() {
        BasePage.initialization();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void shoppingScenario() {
        // Retrieve email and password from properties and log in
        String email = credentials.getProperty("email");
        String password = credentials.getProperty("password");
        homePage.login(email, password);

        // Navigate to the Oxfords Shoes page
        homePage.hoverOnShoesAndSelectOxfords();

        // Apply price filter and select Free Shipping
        productPage.applyPriceFilter("100", "10000");
        productPage.selectFreeShipping();

        // Assert that price filter is applied correctly
        Assert.assertTrue(driver.getCurrentUrl().contains("100-10000"), "Price filter not applied correctly.");

        // Add 10 products to the cart
        for (int i = 0; i < 10; i++) {
            productPage.selectProduct(i);
            driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
            productDetailsPage.addToCart();

            // Assert that the product was added to the cart
            Assert.assertTrue(driver.findElement(By.cssSelector("div[class='shop-cart--responsive--NnGUleD'] span[class='shop-cart--number--axE62FE']")).getText().contains(String.valueOf(i + 1)), "Product " + (i + 1) + " was not added to the cart.");

            // Close the tab and switch back to the main window
            productDetailsPage.closeTabAndSwitchBack();
        }

        // Navigate to the cart page and remove the first 2 items
        cartPage.removeFirstItem();
        cartPage.removeFirstItem();

        // Assert that the first two items have been removed (check cart count or item status)
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='shop-cart--responsive--NnGUleD'] span[class='shop-cart--number--axE62FE']")).getText(), "8", "Failed to remove the first two items from the cart.");

        // Proceed to checkout
        cartPage.proceedToCheckout();

        // Assert that the user is on the checkout page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout"), "Not redirected to the checkout page.");
    }
}
