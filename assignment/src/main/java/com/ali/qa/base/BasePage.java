package com.ali.qa.base;

import com.ali.qa.utilities.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static WebDriver driver;
    public static Properties prop;
    public static Properties credentials;

    public BasePage(WebDriver driver) {
        prop = new Properties();
        credentials = new Properties();
        try (FileInputStream ip = new FileInputStream("src/main/java/com/ali/qa/configurations/configurations.properties")) {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream creds = new FileInputStream("src/main/java/com/ali/qa/configurations/credentials.properties")) {
            credentials.load(creds);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {
        String browserName = prop.getProperty("browser");
        String chromeDriverPath = prop.getProperty("chromeDriverPath");
        String geckoDriverPath = prop.getProperty("geckoDriverPath");

        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", geckoDriverPath);
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Unsupported browser: " + browserName);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));
    }
}
