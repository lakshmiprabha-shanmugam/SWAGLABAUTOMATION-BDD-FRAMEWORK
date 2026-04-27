package com.swagalabs.stepdefinitions;

import com.swagalabs.pages.InventoryPage;
import com.swagalabs.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStepDefinitions {

    private final LoginPage     loginPage     = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();

    // ════════════════════════════════════════════════════════════════════════════
    // Given
    // ════════════════════════════════════════════════════════════════════════════

    @Given("the user is on the Swag Labs login page")
    public void theUserIsOnTheSwagLabsLoginPage() {
        loginPage.navigateTo("https://www.saucedemo.com/");
    }

    // ════════════════════════════════════════════════════════════════════════════
    // When — Login actions
    // ════════════════════════════════════════════════════════════════════════════

    @When("the user enters username {string} and password {string}")
    public void theUserEntersUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        loginPage.clickLoginButton();
    }

    // US-03: partial field entry
    @When("the user enters only username {string}")
    public void theUserEntersOnlyUsername(String username) {
        loginPage.enterUsernameOnly(username);
    }

    @When("the user enters only password {string}")
    public void theUserEntersOnlyPassword(String password) {
        loginPage.enterPasswordOnly(password);
    }

    // US-05 AC-05: Enter key submission
    @When("the user submits the form using the Enter key")
    public void theUserSubmitsTheFormUsingTheEnterKey() {
        loginPage.submitWithEnterKey();
    }

    // US-05 AC-03: viewport resize
    @When("the browser is resized to mobile viewport")
    public void theBrowserIsResizedToMobileViewport() {
        loginPage.resizeBrowserTo(375, 812);
    }

    // US-01 AC-03 / US-06 AC-01: cart navigation
    @When("the user clicks the cart icon")
    public void theUserClicksTheCartIcon() {
        inventoryPage.clickCartIcon();
    }

    // US-06 AC-02: logout
    @When("the user opens the burger menu and clicks Logout")
    public void theUserOpensTheBurgerMenuAndClicksLogout() {
        inventoryPage.openBurgerMenuAndLogout();
    }

    // ════════════════════════════════════════════════════════════════════════════
    // Then / And — US-01: Redirect and inventory
    // ════════════════════════════════════════════════════════════════════════════

    @Then("the user should be redirected to the inventory page")
    public void theUserShouldBeRedirectedToTheInventoryPage() {
        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Expected URL to contain 'inventory' but was: " + loginPage.getCurrentUrl());
    }

    @Then("the page title should display {string}")
    public void thePageTitleShouldDisplay(String expectedTitle) {
        Assert.assertEquals(inventoryPage.getAppLogoText(), expectedTitle,
                "App logo title mismatch");
    }

    @And("the page heading should display {string}")
    public void thePageHeadingShouldDisplay(String expectedHeading) {
        Assert.assertEquals(inventoryPage.getPageHeading(), expectedHeading,
                "Page heading mismatch");
    }

    @And("the product items should be visible on the page")
    public void theProductItemsShouldBeVisibleOnThePage() {
        Assert.assertTrue(inventoryPage.areProductsVisible(),
                "No product items found on the inventory page");
    }

    @Then("the cart icon should be visible")
    public void theCartIconShouldBeVisible() {
        Assert.assertTrue(inventoryPage.isCartIconVisible(),
                "Cart icon is not visible");
    }

    @And("the burger menu should be visible")
    public void theBurgerMenuShouldBeVisible() {
        Assert.assertTrue(inventoryPage.isBurgerMenuVisible(),
                "Burger menu is not visible");
    }

    @Then("the user should be navigated to the cart page")
    public void theUserShouldBeNavigatedToTheCartPage() {
        Assert.assertTrue(inventoryPage.isOnCartPage(),
                "Expected URL to contain 'cart' but was: " + loginPage.getCurrentUrl());
    }

    // ════════════════════════════════════════════════════════════════════════════
    // Then / And — US-02: Failed login state
    // ════════════════════════════════════════════════════════════════════════════

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Expected an error message but none was shown");
    }

    @And("the error message should contain {string}")
    public void theErrorMessageShouldContain(String expectedText) {
        String actual = loginPage.getErrorMessage();
        Assert.assertTrue(actual.contains(expectedText),
                "Expected error to contain: '" + expectedText + "' but got: '" + actual + "'");
    }

    @Then("the user should remain on the login page")
    public void theUserShouldRemainOnTheLoginPage() {
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "Expected user to remain on login page but URL was: " + loginPage.getCurrentUrl());
    }

    @And("the password field should be cleared")
    public void thePasswordFieldShouldBeCleared() {
        Assert.assertTrue(loginPage.isPasswordFieldEmpty(),
                "Expected password field to be empty after failed login");
    }

    // ════════════════════════════════════════════════════════════════════════════
    // Then / And — US-05: UI and Accessibility
    // ════════════════════════════════════════════════════════════════════════════

    @Then("the username field should be visible")
    public void theUsernameFieldShouldBeVisible() {
        Assert.assertTrue(loginPage.isUsernameFieldVisible(),
                "Username field is not visible");
    }

    @And("the password field should be visible")
    public void thePasswordFieldShouldBeVisible() {
        Assert.assertTrue(loginPage.isPasswordFieldVisible(),
                "Password field is not visible");
    }

    @And("the login button should be visible")
    public void theLoginButtonShouldBeVisible() {
        Assert.assertTrue(loginPage.isLoginButtonVisible(),
                "Login button is not visible");
    }

    @Then("the password field should be of type {string}")
    public void thePasswordFieldShouldBeOfType(String expectedType) {
        Assert.assertEquals(loginPage.getPasswordFieldType(), expectedType,
                "Password field type mismatch");
    }

    @Then("the username field should have placeholder {string}")
    public void theUsernameFieldShouldHavePlaceholder(String expectedPlaceholder) {
        Assert.assertEquals(loginPage.getUsernamePlaceholder(), expectedPlaceholder,
                "Username placeholder mismatch");
    }

    @And("the password field should have placeholder {string}")
    public void thePasswordFieldShouldHavePlaceholder(String expectedPlaceholder) {
        Assert.assertEquals(loginPage.getPasswordPlaceholder(), expectedPlaceholder,
                "Password placeholder mismatch");
    }

    // ════════════════════════════════════════════════════════════════════════════
    // Then / And — US-06: Session persistence
    // ════════════════════════════════════════════════════════════════════════════

    @And("the user should still be logged in")
    public void theUserShouldStillBeLoggedIn() {
        Assert.assertTrue(inventoryPage.isUserLoggedIn(),
                "Expected user to remain logged in but was redirected to login page");
    }

    @Then("the user should be returned to the login page")
    public void theUserShouldBeReturnedToTheLoginPage() {
        Assert.assertTrue(inventoryPage.isOnLoginPage(),
                "Expected login page after logout but URL was: " + loginPage.getCurrentUrl());
    }

    @And("the session should be cleared")
    public void theSessionShouldBeCleared() {
        Assert.assertTrue(inventoryPage.isSessionCleared(),
                "Expected login button to be visible after logout but it was not found");
    }
}
