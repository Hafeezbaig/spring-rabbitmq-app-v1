# Java Spring Boot RabbitMQ Integration

This project demonstrates how to integrate RabbitMQ with a Spring Boot application using different exchange types:
- Direct Exchange
- Fanout Exchange
- Topic Exchange

---

## Setup and Running the Application

### 1. Start RabbitMQ in Docker
If RabbitMQ is already installed, start it:
```
docker-compose -f docker/docker-compose.yml up -d
```

If RabbitMQ is not installed, run this command to create and start a new RabbitMQ container:
```
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

### 2. Verify RabbitMQ Is Running
Visit:  
http://localhost:15672

Login credentials:
- Username: guest
- Password: guest

---

## Build and Run the Spring Boot Application

### 1. Clean and Reinstall Dependencies
```
mvn clean install
```

### 2. Start the Spring Boot Application
```
mvn spring-boot:run
```

### 3. Check Running Ports
```
lsof -i :9100
```

### 4. Kill a Process Using Port 9100
```
kill -9 <PID>
```

---

## API Endpoints (Using Postman or Curl)

### 1. Send Message to Direct Exchange
```
curl -X POST http://localhost:9100/api/produce \
     -H "Content-Type: application/json" \
     -d '{"message": "Hello, RabbitMQ!"}'
```

Expected Response:
```
{"statusCode":202,"info":"Acknowledged"}
```

---

### 2. Receive a Message from the Queue
```
curl -X GET http://localhost:9100/api/consume
```

Expected Response:
```
Hello, RabbitMQ!
```

---

### 3. Send Message to Fanout Exchange
```
curl -X POST http://localhost:9100/api/fanout \
     -H "Content-Type: application/json" \
     -d '{"message": "Broadcast to all consumers"}'
```

---

### 4. Send Message to Topic Exchange
```
curl -X POST "http://localhost:9100/api/topic?key=user.created" \
     -H "Content-Type: application/json" \
     -d '{"message": "User created event"}'
```

---

## Stopping the Application

### Stop Spring Boot
Use Ctrl + C in the terminal where Spring Boot is running

### Stop RabbitMQ
```
docker stop rabbitmq-container
```

---

## Restarting Everything

```
docker-compose -f docker/docker-compose.yml up -d
mvn spring-boot:run
```

---

## Postman Collection

See `SpringMessageBrokerAPI.postman_collection.json` for ready-to-use API tests.

---

## API Documentation (Swagger UI)

Swagger UI is integrated and provides an interactive way to view and test all API endpoints.

- Visit: [http://localhost:9100/swagger-ui/index.html](http://localhost:9100/swagger-ui/index.html)

This interface allows you to:
- Send requests and view responses
- Explore each exchange type and its behavior
- Understand request/response formats for all endpoints

---

## Highlights

- Direct Exchange-based messaging
- Fanout Exchange broadcasting
- Topic Exchange pattern-based routing
- All exchanges configured in `RabbitMQConfig.java`

---

