Feature: Check for battery capacity for a specific drone

  Scenario Outline: 1. Check for battery capacity for an existing drone
    Given the following drones are registered
      | # | ID | Serial number | Model          | Weight limit | Battery capacity     | State      |
      | 1 | D1 | D_441_01      | LIGHT_WEIGHT   | 100          | 94                   | IDLE       |
      | 2 | D2 | D_441_02      | MIDDLE_WEIGHT  | 200          | <Battery percentage> | LOADING    |
      | 3 | D3 | D_441_03      | MIDDLE_WEIGHT  | 300          | 52                   | LOADED     |
      | 4 | D4 | D_441_04      | CRUISER_WEIGHT | 400          | 12                   | DELIVERING |
      | 5 | D5 | D_441_05      | HEAVY_WEIGHT   | 500          | 60                   | DELIVERED  |
      | 6 | D6 | D_441_06      | CRUISER_WEIGHT | 350          | 84                   | RETURNING  |
    When user checks for battery level for drone "D2"
    Then the returned battery percentage should be <Battery percentage>
    Examples:
      | Battery percentage |
      | 0                  |
      | 10                 |
      | 50                 |
      | 99                 |
      | 100                |


  Scenario: 2. Check for battery capacity for a non-existing drone
    Given the following drones are registered
      | # | ID | Serial number | Model          | Weight limit | Battery capacity | State      |
      | 1 | D1 | D_441_01      | LIGHT_WEIGHT   | 100          | 94               | IDLE       |
      | 2 | D2 | D_441_02      | MIDDLE_WEIGHT  | 200          | 21               | LOADING    |
      | 3 | D3 | D_441_03      | MIDDLE_WEIGHT  | 300          | 52               | LOADED     |
      | 4 | D4 | D_441_04      | CRUISER_WEIGHT | 400          | 12               | DELIVERING |
      | 5 | D5 | D_441_05      | HEAVY_WEIGHT   | 500          | 60               | DELIVERED  |
      | 6 | D6 | D_441_06      | CRUISER_WEIGHT | 350          | 84               | RETURNING  |
    When user checks for battery level for drone "D8"
    Then an error is returned with status code 404 with a message "Drone not found"
