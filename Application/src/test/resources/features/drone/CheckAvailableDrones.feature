Feature: Check for available drone

  Scenario: 1. Check for available drones (1 available)
    Given the following drones are registered
      | # | ID | Serial number | Model          | Weight limit | Battery capacity | State      |
      | 1 | D1 | D-441-01      | LIGHT_WEIGHT   | 100          | 94               | IDLE       |
      | 2 | D2 | D-441-02      | MIDDLE_WEIGHT  | 200          | 21               | LOADING    |
      | 3 | D3 | D-441-03      | MIDDLE_WEIGHT  | 300          | 52               | LOADED     |
      | 4 | D4 | D-441-04      | CRUISER_WEIGHT | 400          | 12               | DELIVERING |
      | 5 | D5 | D-441-05      | HEAVY_WEIGHT   | 500          | 60               | DELIVERED  |
      | 6 | D6 | D-441-06      | CRUISER_WEIGHT | 350          | 84               | RETURNING  |
    When user checks for available drones
    Then the following drone(s) should return
      | # | ID | Serial number | Model        | Weight limit | Battery capacity | State |
      | 1 | D1 | D-441-01      | LIGHT_WEIGHT | 100          | 94               | IDLE  |

  Scenario: 2. Check for available drones (many available)
    Given the following drones are registered
      | # | ID | Serial number | Model          | Weight limit | Battery capacity | State     |
      | 1 | D1 | D-441-01      | LIGHT_WEIGHT   | 100          | 94               | IDLE      |
      | 2 | D2 | D-441-02      | MIDDLE_WEIGHT  | 200          | 21               | LOADING   |
      | 3 | D3 | D-441-03      | MIDDLE_WEIGHT  | 300          | 52               | LOADED    |
      | 4 | D4 | D-441-04      | CRUISER_WEIGHT | 400          | 12               | IDLE      |
      | 5 | D5 | D-441-05      | HEAVY_WEIGHT   | 500          | 60               | IDLE      |
      | 6 | D6 | D-441-06      | CRUISER_WEIGHT | 350          | 84               | RETURNING |
    When user checks for available drones
    Then the following drone(s) should return
      | # | ID | Serial number | Model          | Weight limit | Battery capacity | State |
      | 1 | D1 | D-441-01      | LIGHT_WEIGHT   | 100          | 94               | IDLE  |
      | 4 | D4 | D-441-04      | CRUISER_WEIGHT | 400          | 12               | IDLE  |
      | 5 | D5 | D-441-05      | HEAVY_WEIGHT   | 500          | 60               | IDLE  |


  Scenario: 3. Check for available drones (0 available)
    Given the following drones are registered
      | # | ID | Serial number | Model          | Weight limit | Battery capacity | State      |
      | 1 | D1 | D-441-01      | LIGHT_WEIGHT   | 100          | 94               | DELIVERING |
      | 2 | D2 | D-441-02      | MIDDLE_WEIGHT  | 200          | 21               | LOADING    |
      | 3 | D3 | D-441-03      | MIDDLE_WEIGHT  | 300          | 52               | LOADED     |
      | 4 | D4 | D-441-04      | CRUISER_WEIGHT | 400          | 12               | DELIVERING |
      | 5 | D5 | D-441-05      | HEAVY_WEIGHT   | 500          | 60               | DELIVERED  |
      | 6 | D6 | D-441-06      | CRUISER_WEIGHT | 350          | 84               | RETURNING  |
    When user checks for available drones
    Then the following drone(s) should return
      | # | ID | Serial number | Model | Weight limit | Battery capacity | State |
