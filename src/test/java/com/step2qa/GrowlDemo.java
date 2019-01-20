package com.step2qa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Rahul R on 1/20/2019
 * @version 1.0.1
 */
public class GrowlDemo {

    private WebDriver driver;

    @BeforeTest
    public void setup() throws InterruptedException {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--test-type");
        options.addArguments("--allow-running-insecure-content");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(capabilities);

        driver.manage().window().maximize();
        driver.get("https://www.google.com");

        Common.loadJQuery(driver);

    }

    @Test
    public void openStep2QA_chrome() throws InterruptedException {

        Common.growlStep(driver, "Open Google and Search Step2QA");
        driver.findElement(By.name("q")).sendKeys("Step2QA");

        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        Common.growlNotice(driver, "Wait for 5 seconds");
        Thread.sleep(5000);

        Common.growlStep(driver, "Click on Step2QA link");
        driver.findElement(By.xpath("//ol//a[contains(text(),'Elevate Quality Engineering')]")).click();

        Common.growlNotice(driver, "Wait for the page load");
        Thread.sleep(5000);

        Common.growlWarning(driver, "Verify Step2QA homepage opens");
        Assert.assertEquals(driver.getTitle(), "Step2QA – Elevate Quality Engineering", "Failed to open the clicked site.");

    }

    @AfterTest
    public void teardown() throws InterruptedException {
        Common.growlError(driver, "Exiting the browser, please check logs if any error occurred.");
        driver.quit();
    }

}
