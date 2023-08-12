Feature: Customers

 Background:
    Given User should open chrome browser
    When User enters URL "http://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login button
    Then User can view Dashboad

  @regression @sanity
  Scenario: Add new customers

    When User click on customers Menu
    And click on customers Menu Item
    And click on Add new button
    Then User can view Add new customer page
    When User enter customer info
    And click on Save button
    Then User can view confirmation message "The new customer has been added successfully."
    And close browser

  @regression
  Scenario: Search Customer by Email

    When User click on customers Menu
    And click on customers Menu Item
    And Enter customer EMail
    When Click on search button
    Then User should found Email in the Search table
    And close browser

  @sanity
  Scenario: Search Customer by Name

    When User click on customers Menu
    And click on customers Menu Item
    And Enter customer FirstName
    And Enter customer LastName
    When Click on search button
    Then User should found Name in the Search table
    And close browser