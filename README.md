# BibalSys
The purpose of this project is to analyze, design and build an application for management of a library that will allow the end user (the librarian) to create, update and destroy information relating to oeuvre, users, borrow and reservations from a specific specification.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

create an empty database on MariaDB as "bibal"
```
create database bibal;
```

use Spring boot (with integred tomcat server)

### Installing

development env running
* eclipse
* Spring tools
* Maven
* SGBD : MariaDB


## JUnit tests

automated tests for this system are available in the folder: ./Bibal/src/test/java/com/bibal/


## Deployment

run ./src/main/java/com/bibal/BibalApplication.java  as Java Application
the Application will run on local host => open http://localhost:8080/index on the navigator

## Built With

* **Spring MVC** - The web framework used
* **Maven** - Dependency Management
* **Spring boot** - The local server with "tomcat"
* **bootstrap** - The front-end framework used
* **Thymeleaf** - The server-side Java template engine used in HTML files

## Contributing

This project is developed for academic purposes, please only idSaid and Med-anas are allowed to commit and push


## Author

* [Mounir Ez-zahar](https://github.com/mounirzz)

See also the list of [contributors](https://github.com/mounirzz/BibalSys/contributors) who participated in this project.
