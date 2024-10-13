# User Expense Management System
In this project we have created set of microservices which allows users to manage their finances by setting budgets, tracking expenses and
receiving alerts for budget exceedances.

## Getting Started
Here I have used Eureka as a discovery server, OpenFeign for communication, spring-doc open API for swagger specification, starter-mail for sending notification emails and H2 in memory database.

## Architecture
This application contains below modules:

- **service-registry** : Eureka is a service registry that allows microservices to register and discover other services.
- **api-gateway** : API gateway is a key component that serves as a single entry point for clients to access various services.
- **budget-service** : Budget Service is helps to create/update/view/delete budgets for user.
- **expense-service** : Expense Service is helps to create/update/view/delete expenses for user. User will get an Alert email (notification) from system if budget exceeds.

## Pre-requisite
    Java 17
    Maven

## How to run application 
- Import all modules in your IDE.
- Run `mvn clean verify` by going inside each folder to build the applications.
- After that run `mvn spring-boot:run` by going inside each folder to start the applications.
- Make sure you follow correct sequence as below.

    * service-registry
    * api-gateway
    * budget-service
    * expense-service
      
##  Email notification module
To send an alert(notifications) to user when Expenses are more then Budget.
I have migrated [mailtrap](https://mailtrap.io/) to our notification service. for demo purpose I am using free trial version of this. due to restriction of free trial I am able to send an email to the only person who has registered on mailtrap with email id. In my case I have configured `uboriwala@gmail.com` for demo purpose.

below is the screenshot for reference:

<img width="732" alt="image" src="https://github.com/user-attachments/assets/7b200da3-0cf2-41f6-b973-db1ad611c1c4">

## Swagger & h2-console
Once application is up and running, you can access swagger-ui using below endpoints:

- [swagger-ui for budget-service](http://localhost:8081/swagger-ui/index.html)
- [swagger-ui for expense-service](http://localhost:8082/swagger-ui/index.html)

H2-console:
- [budget-service](http://localhost:8081/h2-console)
- [expense-service](http://localhost:8082/h2-console)

