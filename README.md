# User Expense Management System
In this project we have created set of microservices which allows users to manage their finances by setting budgets, tracking expenses and
receiving alerts for budget exceedances.

## Getting Started
Here I have used Eureka as a discovery server, OpenFeign for communication, spring-doc open API for swagger specification, starter-mail for sending notification emails.

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
<img width="598" alt="image" src="https://github.com/user-attachments/assets/4cbc4e4e-5fe8-4c28-9f60-4e23e61e042d">

