Feature: Account API testing

  Scenario: POST request to create new user
    Given User is on demoQA website
    When User creates a new user using the POST method
    Then Status Response is 200


  Scenario: POST request to generate token
    Given User is on demoQA website
    When User generates token
    Then Token is generated successfully

  Scenario:  POST request for authorization
    Given User is an authorized user
    And User is on demoQA website
    When User makes a POST method for authorization
    Then Status Response will be 200
    And Response header will be displayed

  Scenario: Delete request to delete the user
    Given User is an authorized user
    And User is on demoQA website
    When User deletes a user
    Then User is successfully deleted