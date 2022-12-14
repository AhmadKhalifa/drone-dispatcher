Feature: Register a new drone

  Background:
    Given configured max allowed serial number length is 10
    And configured max allowed drone weight is 500 grams
    And the following drones are registered
      | # | ID | Serial number | Model          | Weight limit | Battery capacity | State      |
      | 1 | D1 | D_441_01      | LIGHT_WEIGHT   | 100          | 94               | IDLE       |
      | 2 | D2 | D_441_02      | MIDDLE_WEIGHT  | 200          | 21               | LOADING    |
      | 3 | D3 | D_441_03      | MIDDLE_WEIGHT  | 300          | 52               | LOADED     |
      | 4 | D4 | D_441_04      | CRUISER_WEIGHT | 400          | 12               | DELIVERING |
      | 5 | D5 | D_441_05      | HEAVY_WEIGHT   | 500          | 60               | DELIVERED  |
      | 6 | D6 | D_441_06      | CRUISER_WEIGHT | 350          | 84               | RETURNING  |


  Scenario: 1. Register a new drone with a unique and valid serial number
    When user registers a new drone with the following details
      | Serial number | Model        | Weight limit |
      | D_441_7       | LIGHT_WEIGHT | 150          |
    Then drone is registered successfully with serial number "D_441_7"


  Scenario: 2. Register a new drone with a unique but not valid serial number (length > max configured)
    When user registers a new drone with the following details
      | Serial number | Model        | Weight limit |
      | D_441_39123_7 | LIGHT_WEIGHT | 150          |
    Then an error is returned with status code 400 with a message "Drone serial number is too long"


  Scenario: 3. Register a new drone with a valid but non-unique serial number
    When user registers a new drone with the following details
      | Serial number | Model        | Weight limit |
      | D_441_05       | LIGHT_WEIGHT | 150          |
    Then an error is returned with status code 409 with a message "Drone already registered with this serial number"


  Scenario: 4. Register a new drone with an invalid model
    When user registers a new drone with the following details
      | Serial number | Model       | Weight limit |
      | D_441_7       | PHOTOGRAPHY | 150          |
    Then an error is returned with status code 400 with a message "Invalid drone model"


  Scenario: 5. Register a new drone with an invalid weight limit (weight > max configured)
    When user registers a new drone with the following details
      | Serial number | Model        | Weight limit |
      | D_441_7       | HEAVY_WEIGHT | 600          |
    Then an error is returned with status code 400 with a message "Drone max weight is more than max allowed"


  Scenario Outline: 6. Register a new drone with an invalid weight limit (weight < 1)
    When user registers a new drone with the following details
      | Serial number | Model        | Weight limit         |
      | D_441_7       | LIGHT_WEIGHT | <Drone weight limit> |
    Then an error is returned with status code 400 with a message "Drone max weight can't be zero or negative"
    Examples:
      | Drone weight limit |
      | 0                  |
      | -1                 |
      | -10                |
