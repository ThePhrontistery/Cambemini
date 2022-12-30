# Canbemini 
Easily create Kanbans in a moment.

## Features
* As a user you can have different Kanbans for different process flow.
* As a User you would to be able to easily create and configure a Kanban with a very simple UI so you can be up and running in 5 minutes without contacting IT.
* You would to use the swimlanes (is used in our kanban, that visually distinguishes job sharing and responsibilities for sub-processes of a business process. Swimlanes may be arranged horizontally) for state management and not the notes so that I can keep the notes very simple and text based.
* You would to be able to access the Kanban together with other users and be able to move and manipulate the notes (text-based card, which can be moved through the different swimlanes) by all users.
* You would to be able to control access to the Kanban and share it with my coworkers so we can work in private with a select group of people.
* You would to be able to add some attachments to each note so you can add more information to the note. Given that the notes are text based and you don't see the attachments inline, when you click on an attachment then you want to be able to either view it online or download.
* You would to do shared kanbans with different roles like owner, editor and viewer so you can control who can modify, delete notes, swimlanes... 
* As a user owner of a kanban, I can my shared teammates to be collaborator role, so I can ensure if someone resend the url, the no-wanted user can not delete or modify notes/Swimlanes.
* As a user editor, you can do the same as owner, except deleting the hole Kanban or switch a user into owner role.
* As a user collaborator, you only can move swimlanes and notes.



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
* ---open a terminal---
* Install necessary dependencies: npm install
* Run the project: ng serve
* Open the browser at http://localhost:4200 to see the project in action


## Running the application locally

* There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `es.capgemini.cca.canbemini.CanbemeniApplication` class from your IDE.

* A second way to run the Spring Boot application, is open a terminal in canbemini/api and running the following command: gradle bootRun. [More info about bootRun](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#running-your-application)


## How to use

<img width="517" alt="image" src="https://user-images.githubusercontent.com/102371536/210051462-a586f042-310c-4075-90a4-108f97d94aeb.png">

Enter the application through the browser of your choice, log in with: cesar@email.com, mercedes@email.com, jacques@email.com, fredy@email.com or raul@email.com, 
the password is "123" in all cases.

<img width="952" alt="image" src="https://user-images.githubusercontent.com/102371536/210051781-ecdf83ca-5d96-45c0-a979-c5ad53b4a0bc.png">

This is the list of Kanbans

<img width="958" alt="image" src="https://user-images.githubusercontent.com/102371536/210052568-d769220b-7616-42a6-8b29-a8d5f1a23f1c.png">

When we click on a Kanban from the list of Kanbans, we have this interface for editing the selected Kanban.


## Copyright
Released under the Apache License 2.0.