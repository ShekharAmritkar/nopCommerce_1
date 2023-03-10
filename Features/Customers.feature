Feature: Customers

  Background: Common steps for each scenario
    Given User Launch Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then User can view Dashboard
    When User clicks on customers Menu
    And click on customers Menu Item

@sanity
  Scenario: Add new customer
    And click on Add new button
    Then User can view Add new customer page
    When User enter customer info
    And click on Save button
    Then User can view confirmation massage "The new customer has been added successfully."
    And Close browser

  @regression
  Scenario: Search Customer by EMailID
    And Enter customer EMail
    When Click on search button
    Then User should found Email in the Search table
    And Close browser

  @regression
  Scenario: Search Customer by Name
    And Enter customer FirstName
    And Enter customer LastName
    When Click on search button
    Then User should found Name in the Search table
    And Close browser

