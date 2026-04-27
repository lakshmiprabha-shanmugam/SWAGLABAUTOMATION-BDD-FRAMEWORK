Feature: US06 - Session Persistence After Login
  As a logged-in user
  I want my session to remain active while I navigate the application
  So that I do not have to log in repeatedly during a single visit

  # AC-01: Session persists when navigating between pages
  Scenario: AC-01 - User remains logged in when navigating between Products and Cart
    Given the user is on the Swag Labs login page
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should be redirected to the inventory page
    When the user clicks the cart icon
    Then the user should be navigated to the cart page
    And the user should still be logged in

  # AC-02: Logout returns user to login page and clears session
  Scenario: AC-02 - User is returned to login page and session is cleared after logout
    Given the user is on the Swag Labs login page
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should be redirected to the inventory page
    When the user opens the burger menu and clicks Logout
    Then the user should be returned to the login page
    And the session should be cleared
