# API-Gateway



## Getting started
In a Microservices architecture, an API gateway is a key component that serves as a single entry point for clients to access various services. It acts as a reverse proxy that routes requests from clients to the appropriate microservice.

## Sequence 
Once [service-registry](https://github.com/umangboriwala/user-financial-management/tree/main/service-registry) service started successfully and Eureka Discovery server is up and running, run [ApiGatewayApplication.java](https://github.com/umangboriwala/user-financial-management/blob/main/api-gateway/src/main/java/io/umang/project/api_gateway/ApiGatewayApplication.java) file. It will get registered it-self with Eureka Service Registry.  