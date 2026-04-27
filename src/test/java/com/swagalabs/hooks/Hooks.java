package com.swagalabs.hooks;

import com.swagalabs.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        DriverFactory.initDriver(System.getProperty("browser", "chrome"));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario FAILED: " + scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}
