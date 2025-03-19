# **Java Spring Boot RabbitMQ Integration**

This project demonstrates how to integrate **RabbitMQ** with a **Spring Boot** application.

---

## **Setup and Running the Application**

### **1. Start RabbitMQ in Docker**
If RabbitMQ is already installed, start it:
```sh
docker start rabbitmq
```  
If RabbitMQ is not installed, run this command to create and start a new RabbitMQ container:
```sh
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```  

### **2. Verify RabbitMQ Is Running**
Check if RabbitMQ is running by visiting:  
[http://localhost:15672](http://localhost:15672)

**Login Credentials:**
- **Username:** `guest`
- **Password:** `guest`

---

## **Build and Run the Spring Boot Application**

### **1. Clean and Reinstall Dependencies**
```sh
mvn clean install
```  

### **2. Start the Spring Boot Application**
```sh
mvn spring-boot:run
```  

### **3. Check Running Ports**
If the application is not starting, check if another process is using port 8080:
```sh
lsof -i :8080
```  

### **4. Kill a Process Using Port 8080**
If another process is blocking the port, stop it using:
```sh
kill -9 <PID>
```  

---

## **API Endpoints (Using Postman or Curl)**

### **Send a Message (Producer)**
- **Method:** `POST`
- **URL:**
  ```
  http://localhost:8080/producer/HelloWorld
  ```
- **Expected Response:**
  ```
  Message sent: HelloWorld
  ```

---

### **Receive a Message (Consumer)**
- **Method:** `GET`
- **URL:**
  ```
  http://localhost:8080/consumer
  ```
- **Expected Response:**
  ```
  HelloWorld
  ```

---

## **Stopping the Application**

### **Stop Spring Boot**
```sh
Ctrl + C  # In terminal where Spring Boot is running
```  

### **Stop RabbitMQ**
```sh
docker stop rabbitmq
```  

---

## **Restarting Everything**

When you want to start the project again, follow these steps:

1. **Start RabbitMQ**
```sh
docker start rabbitmq
```  
2. **Run Spring Boot**
```sh
mvn spring-boot:run
```  
3. **Test the APIs in Postman or Curl**

---

