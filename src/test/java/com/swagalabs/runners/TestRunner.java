package com.swagalabs.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features  = "src/test/resources/features",
        glue      = {
                "com.swagalabs.stepdefinitions",
                "com.swagalabs.hooks"
        },
        plugin    = {
                // Console output
                "pretty",

                // Built-in Cucumber reports
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",

                // Allure – picks up steps, tags, and attachments
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",

                // Extent Spark – interactive HTML report
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
