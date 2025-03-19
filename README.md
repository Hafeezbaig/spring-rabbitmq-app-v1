# Java Spring Boot RabbitMQ Integration

This project demonstrates how to integrate RabbitMQ with a Spring Boot application.

## Setup and Running the Application

### Clean and Reinstall Dependencies
```sh
mvn clean install
```

### Start the Spring Boot Application
```sh
mvn spring-boot:run
```

### Check Running Ports
```sh
lsof -i :8080
```

### Kill a Process by PID
```sh
kill -9 <PID>
```

## API Endpoints (Using Postman)

### Send a Message (Producer)
**POST** request:  
```sh
http://localhost:8080/producer/HelloWorld
```

### Receive a Message (Consumer)
**GET** request:  
```sh
http://localhost:8080/consumer
```

---

