package com.swagalabs.pages;

import com.swagalabs.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By appLogo       = By.cssSelector(".app_logo");
    private final By pageTitle     = By.cssSelector(".title");
    private final By inventoryList = By.cssSelector(".inventory_item");
    private final By cartIcon      = By.cssSelector("#shopping_cart_container");
    private final By cartLink      = By.cssSelector(".shopping_cart_link");
    private final By burgerMenu    = By.id("react-burger-menu-btn");
    private final By logoutLink    = By.id("logout_sidebar_link");
    private final By menuWrap      = By.cssSelector(".bm-menu-wrap");

    public InventoryPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ── US-01 AC-02: Page title and product visibility ────────────────────────

    public String getAppLogoText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(appLogo)).getText();
    }

    public String getPageHeading() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public boolean areProductsVisible() {
        return !driver.findElements(inventoryList).isEmpty();
    }

    public int getProductCount() {
        return driver.findElements(inventoryList).size();
    }

    // ── US-01 AC-03: Navigation element visibility ────────────────────────────

    public boolean isCartIconVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon)).isDisplayed();
    }

    public boolean isBurgerMenuVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(burgerMenu)).isDisplayed();
    }

    // ── US-01 AC-03 / US-06 AC-01: Cart navigation ───────────────────────────

    public void clickCartIcon() {
        driver.findElement(cartLink).click();
    }

    public boolean isOnCartPage() {
        return driver.getCurrentUrl().contains("cart");
    }

    // ── US-06 AC-01: Session persistence check ───────────────────────────────

    public boolean isUserLoggedIn() {
        String url = driver.getCurrentUrl();
        return url.contains("inventory") || url.contains("cart") || url.contains("checkout");
    }

    // ── US-06 AC-02: Logout flow ─────────────────────────────────────────────

    public void clickBurgerMenu() {
        driver.findElement(burgerMenu).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public void openBurgerMenuAndLogout() {
        clickBurgerMenu();
        clickLogout();
    }

    public boolean isOnLoginPage() {
        return wait.until(driver -> driver.getCurrentUrl().contains("saucedemo.com") &&
                !driver.getCurrentUrl().contains("inventory") &&
                !driver.getCurrentUrl().contains("cart"));
    }

    public boolean isSessionCleared() {
        try {
            WebElement loginBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
            return loginBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBurgerMenuOpen() {
        try {
            WebElement menuContainer = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(menuWrap));
            return "false".equals(menuContainer.getAttribute("aria-hidden"));
        } catch (Exception e) {
            return false;
        }
    }
}
