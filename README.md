# Rate Service

### Description
This project is a REST service that allows CRUD operations on a Rate entity.

### Modules
This project is divided into the following modules:

* Main: Main module that contains the application configuration and startup.
* Api-RestFull: Module that contains the controllers.
* Application: Module that contains the business logic.
* Domain: Module that contains the entities.
* Infrastructure: Module that contains persistence classes and integration with external services.

### Requirements
To compile and run the application, you need:

* JDK ^21
* Maven ^3.9
* Docker ^26.1
* Docker Compose ^2.27

### Installation
To compile and run the application, you need to initialize Docker and then run the following command from the root of the Main module:

```shell
mvn clean install && mvn spring-boot:run
```

### Extras
The project includes a Postman file to test the services. The file is located in the root of the project and is named "RateService.postman_collection.json". Additionally, there is an OpenAPI file with the service documentation in the root of the project named "openapi.yaml".
