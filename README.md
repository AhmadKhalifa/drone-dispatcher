# Drone dispatcher
A reactive drone fleet dispatching system implemented in Java

## Database initialization

The service requires a running `MariaDB` server. To start your own, please run the following

```bash
sudo docker-compose up
```
Tests run on `H2DB` which doesn't require any database containers running.

## BDD 
Behavioural-driven development was followed while implementing this system. Please check the following feature files to get familiar with its business
* [Check available drones](https://github.com/AhmadKhalifa/drone-dispatcher/blob/master/src/test/resources/features/drone/CheckAvailableDrones.feature)
* [Check battery capacity](https://github.com/AhmadKhalifa/drone-dispatcher/blob/master/src/test/resources/features/drone/CheckDroneBatteryCapacity.feature)
* [Register a new drone](https://github.com/AhmadKhalifa/drone-dispatcher/blob/master/src/test/resources/features/drone/RegisterDrone.feature)
* [Drone battery status audit job](https://github.com/AhmadKhalifa/drone-dispatcher/blob/master/src/test/resources/features/logging/DroneBatteryStatusAuditJob.feature)
* [Check drone medications](https://github.com/AhmadKhalifa/drone-dispatcher/blob/master/src/test/resources/features/medication/CheckDroneMedications.feature)
* [Load medications on a drone](https://github.com/AhmadKhalifa/drone-dispatcher/blob/master/src/test/resources/features/medication/LoadDroneWithMedications.feature)
* [Register a new medication](https://github.com/AhmadKhalifa/drone-dispatcher/blob/master/src/test/resources/features/medication/RegisterMedication.feature)

## Usage
To build the application
```bash
./gradlew build
```
To run tests
```bash
./gradlew test
```
To run the application on default port `8080` 
```bash
./gradlew bootRun
```

## Endpoints
All endpoints doesn't require any kind of authorization. Mainly we have 2 controllers `DronesController` and `MedicationsController`
#### Get available drones
```bash
curl --location --request GET 'http://localhost:8080/drones/available'
```

#### Check drone battery
```bash
curl --location --request GET 'http://localhost:8080/drones/:droneUUID/battery'
```

#### Register a new drone
```bash
curl -H "Content-Type: application/json" -X POST \
    -d '{"serialNumber":<SERIAL_NUMBER>, "weightLimit":<WEIGHT>, "model":<MODEL>}' \
    'http://localhost:8080/drones/register'
```

#### Get carried medications by a drone
```bash
curl --location --request GET 'http://localhost:8080/medications/drone/:droneUUID'
```

#### Load medications on a drone
```bash
curl -H "Content-Type: application/json" -X POST \
    -d '{"droneUuid":"<Drone_UUID>", "medications":[{"medicationUuid": <MEDICATION_1_UUID>, "quantity": <MEDICATION_QUANTITY>}, ..., {"medicationUuid": <MEDICATION_N_UUID>, "quantity": <MEDICATION_N_QUANTITY>}]}' \
    'http://localhost:8080/medications/load'
```

#### Register a new medication
```bash
curl -H "Content-Type: application/json" -X POST \
    -d '{"name":<MEDICATION_NAME>, "code":<MEDICATION_CODE>, "weight":<MEDICATION_WEIGHT>, "imageUuid":<IMAGE_UUID>"}' \
    'http://localhost:8080/medications/register'
```



## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.