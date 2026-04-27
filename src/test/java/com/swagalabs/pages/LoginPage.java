package com.swagalabs.pages;

import com.swagalabs.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton   = By.id("login-button");
    private final By errorMessage  = By.cssSelector("[data-test='error']");

    public LoginPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ── Navigation ────────────────────────────────────────────────────────────

    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // ── Login actions ─────────────────────────────────────────────────────────

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterUsernameOnly(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPasswordOnly(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void submitWithEnterKey() {
        driver.findElement(passwordField).sendKeys(Keys.ENTER);
    }

    // ── US-05: UI / Accessibility assertions ──────────────────────────────────

    public boolean isUsernameFieldVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).isDisplayed();
    }

    public boolean isPasswordFieldVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).isDisplayed();
    }

    public boolean isLoginButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }

    public String getPasswordFieldType() {
        return driver.findElement(passwordField).getAttribute("type");
    }

    public String getUsernamePlaceholder() {
        return driver.findElement(usernameField).getAttribute("placeholder");
    }

    public String getPasswordPlaceholder() {
        return driver.findElement(passwordField).getAttribute("placeholder");
    }

    public void resizeBrowserTo(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    // ── US-02 / US-04: Post-failure state assertions ──────────────────────────

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("saucedemo.com") &&
               !driver.getCurrentUrl().contains("inventory") &&
               !driver.getCurrentUrl().contains("cart");
    }

    public boolean isPasswordFieldEmpty() {
        String value = driver.findElement(passwordField).getAttribute("value");
        return value == null || value.isEmpty();
    }

    // ── US-01: Post-login redirect ────────────────────────────────────────────

    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains("inventory");
    }

    // ── Error message ─────────────────────────────────────────────────────────

    public String getErrorMessage() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return error.getText();
    }

    public boolean isErrorDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
