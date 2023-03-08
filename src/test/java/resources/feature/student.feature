Feature: Testing different request on the student application


  Scenario: Check if the student application can be accessed by users
    When User sends a GET request to list endpoint
    Then User must get back a valid status code 200


  Scenario Outline: Create a new student & verify if the student is added
    When I create a new student by providing the information firstName "<firstName>" lastName "<lastName>" email "<email>" programme "<programme>" courses "<courses>"
    Then I verify that the student with email" is created
    Then I verify that the student with "<email>" is created
    Examples:
      | firstName | lastName | email                | programme   | courses |
      | Jane      | Scott    | jane.scott@gmail.com | Science     | Biology |
      | Kiran     | Nayee    | K.nayee@gmail.com    | Api Testing | Java    |


  Scenario: Check if new student info has been updated and verify updated information
    When new student updated with "<firstName>"

    Scenario: check if new student has been deleted by id and verify if deleted
      When I have deleted student by id
      Then I verify that student is deleted





