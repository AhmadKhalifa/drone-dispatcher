# Drone dispatcher

### Getting started document
To get started, please consider the following notes:

* Initialize MariaDB by running docker-compose `sudo docker-compose up`
* Application class is found in Application module `com.drone.dispatcher.application.DroneDispatcherApplication.java`
* Necessary migrations and seeding are done automatically using a migration library
* Development code is using `MariaDB` using profile `local-development`
* Testing code is using `H2DB` using profile `test`
* Application is following clean architecture with modules:
  * `Base` which contains all base code used by all the other modules
  * `Domain` which contains the business logic regardless of any data layer implementation
  * `Data` which contains the actual data layer implementation
  * `Gateway` which contains all the logic of handling outer-world interactions (e.g. controllers)
  * `Application` which contains app-specific code and configuration, in addition to app integration tests
* All modules have their own unit tests except for `Application` modules which contains all integration tests for all modules as a whole
