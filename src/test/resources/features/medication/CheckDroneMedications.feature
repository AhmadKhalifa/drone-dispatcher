Feature: Check for carried medications for a specific drone

  Background:
    Given the following drones are registered
      | # | ID | Serial number | Model          | Weight limit | Battery capacity | State      |
      | 1 | D1 | D_441_01      | LIGHT_WEIGHT   | 100          | 94               | IDLE       |
      | 2 | D2 | D_441_02      | MIDDLE_WEIGHT  | 200          | 21               | LOADING    |
      | 3 | D3 | D_441_03      | MIDDLE_WEIGHT  | 300          | 52               | LOADED     |
      | 4 | D4 | D_441_04      | CRUISER_WEIGHT | 400          | 12               | DELIVERING |
      | 5 | D5 | D_441_05      | HEAVY_WEIGHT   | 500          | 60               | DELIVERED  |
      | 6 | D6 | D_441_06      | CRUISER_WEIGHT | 350          | 84               | RETURNING  |
    And the following medications are registered
      | # | ID | Name              | Code             | Weight | Image ID |
      | 1 | M1 | Aminophylline     | M_432_512_547_01 | 25     | I1       |
      | 2 | M2 | Amyl              | M_432_512_547_02 | 30     | I2       |
      | 3 | M3 | Calcium-Gluconate | M_432_512_547_03 | 100    | I3       |
      | 4 | M4 | Chlorpheniramine  | M_432_512_547_04 | 40     | I4       |
      | 5 | M5 | Digoxin           | M_432_512_547_05 | 120    | I5       |


  Scenario: 1. Check for carried medications for an existing drone (loaded drone, one medication)
    Given drone "D4" is loaded with the following medications
      | Medication ID | Quantity |
      | M2            | 3        |
    When user checks for medications carried by drone "D4"
    Then the following medication(s) should return
      | # | ID | Name | Code             | Weight | Image ID |
      | 2 | M2 | Amyl | M_432_512_547_02 | 30     | I2       |


  Scenario: 2. Check for carried medications for an existing drone (loaded drone, multiple medications)
    Given drone "D4" is loaded with the following medications
      | Medication ID | Quantity |
      | M1            | 2        |
      | M2            | 3        |
      | M3            | 1        |
    When user checks for medications carried by drone "D4"
    Then the following medication(s) should return
      | # | ID | Name              | Code             | Weight | Image ID |
      | 1 | M1 | Aminophylline     | M_432_512_547_01 | 25     | I1       |
      | 2 | M2 | Amyl              | M_432_512_547_02 | 30     | I2       |
      | 3 | M3 | Calcium-Gluconate | M_432_512_547_03 | 100    | I3       |


  Scenario: 3. Check for carried medications for an existing drone (idle drone)
    When user checks for medications carried by drone "D1"
    Then the following medication(s) should return
      | # | ID | Name | Serial number | Model | Weight | Image ID |


  Scenario: 4 Check for carried medications for a non-existing drone
    When user checks for medications carried by drone "D8"
    Then an error is returned with status code 404 with a message "Drone not found"

