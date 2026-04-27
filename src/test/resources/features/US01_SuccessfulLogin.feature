Feature: US01 - Successful Login with Valid Credentials
  As a registered user
  I want to log in with a valid username and password
  So that I can access the Swag Labs product catalogue and all application features

  # AC-01: User is redirected to the Products/Inventory page after valid login
  Scenario: AC-01 - User is redirected to inventory page after valid login
    Given the user is on the Swag Labs login page
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should be redirected to the inventory page

  # AC-02: Products page loads with "Swag Labs" title and products visible
  Scenario: AC-02 - Products page displays title and inventory items after login
    Given the user is on the Swag Labs login page
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    Then the page title should display "Swag Labs"
    And the page heading should display "Products"
    And the product items should be visible on the page

  # AC-03: User can navigate to Cart, Menu, and all app features
  Scenario: AC-03 - User can access Cart and Menu navigation after login
    Given the user is on the Swag Labs login page
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    Then the cart icon should be visible
    And the burger menu should be visible
    When the user clicks the cart icon
    Then the user should be navigated to the cart page
