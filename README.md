# Canbemini 
Easily create Kanbans in a moment

## Features
* 

Canbemeni [Spring Boot](http://projects.spring.io/spring-boot/) and [Angular](https://angular.io/guide/what-is-angular) sample app.

## Application use

- [H2 Data Base](https://www.h2database.com/html/main.html)
- [JPA](https://spring.io/projects/spring-data-jpa)
- [Angular Material](https://material.angular.io/guide/getting-started)

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle 7.6](https://gradle.org/install/)
- [Node.js](https://nodejs.org/en/about/) --> [download node.js 18.12.1 LTS](https://nodejs.org/dist/v18.12.1/node-v18.12.1-x64.msi)

## How to Run 

* [Clone](https://github.com/ThePhrontistery/Canbemini.git) this repository 
* Make sure you are using JDK 17 and Gradle 7.5.1 
---open a terminal---
* Install necessary dependencies: npm install
* Run the project: ng serve
* Open the browser at http://localhost:4200/login to see the project in action


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `es.capgemini.cca.canbemini.CanbemeniApplication` class from your IDE.

Will compile and run the back-end api in port http://localhost:8080  using a gradle task foccused on Spring Boot. [more info about bootRun](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#running-your-application)


## How to use

Enter the application through the browser of your choice, log in with: cesar@email.com, mercedes@email.com, jacques@email.com or raul@email.com, 
the password is "hola" in all cases.

## About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:

**/metrics** Shows “metrics” information for the current application.

**/health** Shows application health information.

**/info** Displays arbitrary application info.

**/configprops** Displays a collated list of all @ConfigurationProperties.

**/mappings** Displays a collated list of all @RequestMapping paths.

**/beans** Displays a complete list of all the Spring Beans in your application.

**/env** Exposes properties from Spring’s ConfigurableEnvironment.

**/trace** Displays trace information (by default the last few HTTP requests).


## Copyright
Released under the Apache License 2.0.

