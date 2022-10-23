Feature: Register a new medication

  Background:
    Given configured medication name regex is "([A-Za-z0-9\-\_]+)"
    And configured medication code regex is "([A-Z0-9\_]+)"
    And the following medications are registered
      | # | ID | Name              | Code             | Weight | Image ID |
      | 1 | M1 | Aminophylline     | M_432_512_547_01 | 25     | I1       |
      | 2 | M2 | Amyl              | M_432_512_547_02 | 30     | I2       |
      | 3 | M3 | Calcium-Gluconate | M_432_512_547_03 | 100    | I3       |
      | 4 | M4 | Chlorpheniramine  | M_432_512_547_04 | 40     | I4       |
      | 5 | M5 | Digoxin           | M_432_512_547_05 | 120    | I5       |


  Scenario: 1. Register a new medication with a unique and valid code
    When user registers a new medication with the following details
      | Name                | Code             | Weight | Image ID |
      | Amphetamine-sulfate | M_432_512_547_06 | 150    | I6       |
    Then medication is registered successfully with code "M_432_512_547_06"


  Scenario Outline: 2. Register a new medication with a unique but invalid code
    When user registers a new medication with the following details
      | Name                | Code              | Weight | Image ID |
      | Amphetamine-sulfate | <Medication code> | 150    | I6       |
    Then an error is returned with status code 400 with a message "Invalid medication code"
    Examples:
      | Medication code |
      | Code_1234       |
      | CODE-1234       |
      | code-1234       |


  Scenario Outline: 3. Register a new drone with invalid name
    When user registers a new medication with the following details
      | Name              | Code             | Weight | Image ID |
      | <Medication name> | M_432_512_547_06 | 150    | I6       |
    Then an error is returned with status code 400 with a message "Invalid medication name"
    Examples:
      | Medication name |
      | Any name        |
      | @ny-name        |


  Scenario: 4. Register a new drone with an existing code
    When user registers a new medication with the following details
      | Name                | Code             | Weight | Image ID |
      | Amphetamine sulfate | M_432_512_547_03 | 150    | I6       |
    Then an error is returned with status code 409 with a message "Medication already registered with this code"
