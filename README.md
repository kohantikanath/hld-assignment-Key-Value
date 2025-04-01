# KeyValueCache

## Overview
KeyValueCache is a Spring Boot application that provides a simple key-value cache service. The cache supports basic operations such as setting and getting values, with an eviction policy to manage memory usage.

## Features
- **Set Key-Value Pairs**: Store key-value pairs in the cache.
- **Get Values**: Retrieve values by key.
- **Eviction Policy**: Automatically evicts 20% of entries when the cache reaches 70% memory usage or exceeds 100,000 items.

## Technologies Used
- **Java**
- **Spring Boot**
- **Maven**
- **Docker**
- **Locust** (for load testing)

## Prerequisites
- Java 17
- Maven
- Docker
- Python 3.x (for Locust)
- Locust (`pip install locust`)

## Getting Started

### Build and Run the Application

1. **Build the JAR file**:
    ```sh
    mvn clean package
    ```

2. **Build the Docker image**:
    ```sh
    docker build -t hldassignment:latest .
    ```

3. **Run the Docker container**:
    ```sh
    docker run -d -p 7171:7171 --name hldassignment hldassignment:latest
    ```

### Load Testing with Locust

1. **Create a `locustfile.py`**:
    
2. **Run Locust**:
    ```sh
    locust -f locustfile.py
    ```

3. **Open Locust Web Interface**:
    - Navigate to `http://localhost:8089` in your web browser to start the load test.

## Configuration
The application can be configured using the `application.properties` file located in `src/main/resources`:
```ini
server.port=7171
spring.application.name=KeyValueCache
logging.level.org.springframework=INFO
