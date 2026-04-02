

# Quiz Application – Microservices Architecture

A scalable Quiz Application built using Microservices Architecture with Spring Boot and Spring Cloud. This project demonstrates service decomposition, inter-service communication, and service discovery using industry-standard tools like Eureka Server and OpenFeign.

---

## Overview

This application is designed as a distributed system where core functionalities are separated into independent services. Each service is responsible for a specific domain and communicates with others through REST APIs.

The architecture ensures:

* High scalability
* Loose coupling between services
* Independent deployment and development

---

## Technology Stack

* Java 21
* Spring Boot
* Spring Cloud Netflix Eureka (Service Discovery)
* OpenFeign (Declarative REST Client)
* RESTful APIs
* Maven (Build Tool)

---

## System Architecture

The application consists of the following microservices:

### Quiz Service

* Handles quiz creation and management
* Aggregates questions from the Question Service
* Evaluates and returns quiz results

### Question Service

* Manages question repository (CRUD operations)
* Provides questions based on category and difficulty
* Acts as a data provider to Quiz Service

### Service Registry (Eureka Server)

* Central registry for all microservices
* Enables dynamic service discovery
* Eliminates the need for hardcoded service URLs

---

## Service Communication

* All services register with the Eureka Server at startup
* Quiz Service communicates with Question Service using OpenFeign
* Communication is performed via REST APIs

---

## Key Features

* Microservices-based architecture
* Dynamic service discovery using Eureka
* Declarative inter-service communication with Feign Client
* Modular and maintainable codebase
* Scalable design principles

---

## Getting Started

### Prerequisites

* Java 21
* Maven
* Git

---

### Run the Application

Start services in the following order:

#### 1. Start Eureka Server

```bash
cd server-registry
mvn spring-boot:run
```

#### 2. Start Question Service

```bash
cd question-service
mvn spring-boot:run
```

#### 3. Start Quiz Service

```bash
cd quiz-service
mvn spring-boot:run
```

---

## API Testing

You can test the APIs using:

* Postman
* cURL
* Browser (for GET endpoints)

---

## Learning Outcomes

This project demonstrates:

* Designing microservices-based systems
* Implementing service discovery using Eureka
* Using Feign Client for inter-service communication
* Building scalable backend services

---

## Author

Harshit Sharma
