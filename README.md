# Payments API
Reactive Payments API

## Build & run app

```
git clone https://github.com/esaenzc/payment-api.git
cd payment-api
mvn package
cd target
java -jar payment-api.jar
```

## Other configuration

Default configuration
```
server:
  port: ${SERVER_PORT:8080}
  hostname: ${SERVER_HOSTNAME:#{'http://localhost:8080'}}
```
Run other configuration

```java
// Server port: Set env variable SERVER_PORT or run with server.port
// Hostname to generate hypermedia links: Set env variable SERVER_HOSTNAME or run with server.hostname
java -jar payment-api.jar --server.port=8090 --server.hostname=http://localhost:8090
```

## Dependencies: 

* Java 8
* Git 
* Maven 3

## Default URLs (running in localhost:8080)
 
* Get all payments: http://localhost:8080/payment/
* Health: http://localhost:8080/actuator/health

## Postman request collection

[Postman request collection to import](payment-api.postman_collection-v2_1.json)


## Design

[Design in PDF](design.pdf)
