# iStore - Microservice Using Spring Cloud - Demo 

![alt text](Microservice-Architecture.jpg "Architecture Diagram")

## IDE - Idea or Eclipse
You can directly run these services from IDE as a spring applicaiton.

## Maven
You can run each service in following command.
mvn spring-boot:run

## Docker

### 1. RabbitMQ - Message Broker
docker run -d --name rabbitmq-management -p 15672:15672 -p 5672:5672 -p 15671:15671 -p 5671:5671 -p 4369:4369 rabbitmq:3-management

### 2. Config Server 
docker run -d -p 8012:8012 -e "spring.rabbitmq.host=172.17.0.2" tonyhe2k/config-server

### 3. Spring Boot Admin

### 4. Eureka - Service Registry/Discovery
docker run -d -p 8761:8761 -e "spring.cloud.config.uri=http://172.17.0.3:8012" tonyhe2k/eureka-server

### 5. Zuul - API Gateway

