package com.swagalabs.hooks;

import com.swagalabs.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        DriverFactory.initDriver(System.getProperty("browser", "chrome"));
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();

        if (driver != null && scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            // Attaches screenshot to Cucumber HTML/JSON and Extent reports
            scenario.attach(screenshot, "image/png",
                    "Screenshot – " + scenario.getName());

            // Attaches screenshot to Allure report
            Allure.addAttachment(
                    "Screenshot – " + scenario.getName(),
                    new ByteArrayInputStream(screenshot)
            );
        }

        DriverFactory.quitDriver();
    }
}
