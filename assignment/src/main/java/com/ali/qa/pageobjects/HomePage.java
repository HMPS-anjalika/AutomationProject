package com.ali.qa.pageobjects;

import com.ali.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    private By signInButton = By.xpath("//div[@class='lgh-contain-login-btn ellipsis']");
    private By emailField = By.xpath("//*[@id=\"batman-dialog-wrap\"]/div/div/div/div[1]/div/div[3]/div[2]/div[1]/span/span[1]/span[1]/input");
//    private By continueButton = By.xpath("//span[normalize-space()='Continue']");
//    private By elementToClickAfterEmail = By.xpath("//*[@id='batman-dialog-wrap']/div/div/div/div[1]/div/div[3]/div[2]/div[2]/div");
    private By passwordField = By.xpath("//*[@id=\"fm-login-password\"]");
    private By popUpSignInButton = By.xpath("//span[normalize-space()='Sign in']");

    private By allCategoriesDropdown = By.xpath("//div[@class='Categoey--categoryTitle--_3bKGRN']");
    private By shoesCategory = By.xpath("//a[@href='https://www.aliexpress.com/p/calp-plus/index.html?categoryTab=shoes']");
    private By oxfordsCategory = By.xpath("//div[@class='Categoey--categoryRight--2uIfSd3']//a[6]//div[1]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        // Click the sign-in button to open the login modal
        driver.findElement(signInButton).click();

        // Wait for the email field to appear and enter the email
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(50));
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailInput.sendKeys(email);

        // Simulate pressing the Enter key after entering the email
        emailInput.sendKeys(Keys.ENTER); // This simulates the "Enter" key press


        // Wait for the password field and enter the password
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordInput.sendKeys(password);

        // Click the final sign-in button
        WebElement signInBtn = wait.until(ExpectedConditions.elementToBeClickable(popUpSignInButton));
        signInBtn.click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement slider = wait2.until(ExpectedConditions.elementToBeClickable(By.id("nc_1_n1z")));
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(300, 0).release().perform(); // Adjust offset as necessary

    }

    public void hoverOnShoesAndSelectOxfords() {
        Actions action = new Actions(driver);

        WebElement closeModalButton = driver.findElement(By.xpath("./html/body/div[5]/div/img"));
        closeModalButton.click(); // Close the overlay/modal

        WebElement categoryElement = driver.findElement(allCategoriesDropdown);
        action.moveToElement(categoryElement).perform();

        WebElement shoesElement = driver.findElement(shoesCategory);
        action.moveToElement(shoesElement).perform();

        WebElement oxfordsElement = driver.findElement(oxfordsCategory);
        oxfordsElement.click();
    }
}
