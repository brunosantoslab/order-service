# Order Service

Order Service is a microservice built with Java and Spring Boot for managing orders in a distributed architecture. It handles creating, processing, and publishing order events using Kafka and PostgreSQL for persistence.

## Features

- Create and manage orders.
- Publish order events to Kafka.
- Process incoming order events.
- PostgreSQL as the database.
- Designed with clean architecture and SOLID principles.

## Tech Stack

- **Java 17**
- **Spring Boot 3.x**
  - Spring Data JPA
  - Spring Kafka
- **PostgreSQL** for persistence.
- **Kafka** for messaging.
- **Docker** for containerization.
- **JUnit 5** for unit and integration testing.

## Prerequisites

Ensure you have the following installed:
- Java 17+
- Maven
- Docker and Docker Compose

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/brunosantoslab/order-service.git
cd order-service
```

### Update the application.yml or set the following environment variables:

- DB_URL=jdbc:postgresql://<host>:5432/<database>
- DB_USERNAME=<username>
- DB_PASSWORD=<password>
- KAFKA_BOOTSTRAP_SERVERS=<kafka-broker>

### Build the Project

```bash
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run
```

### Run unit tests
```bash
mvn test
```

## Docker

### Build the Docker Image
```bash
docker build -t order-service .
```

### Build the Docker Image
```bash
docker build -t order-service .
```

### Run the Docker Container
```bash
docker run -p 8080:8080 --env-file .env order-service .
```

