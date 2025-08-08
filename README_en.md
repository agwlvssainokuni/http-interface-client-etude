# HTTP Interface Client Etude

A sample project for learning Spring Boot 3.5's HTTP Interface functionality.

## Overview

This project aims to learn how to perform HTTP communication declaratively using Spring WebFlux's HTTP Interface. It implements various HTTP communication patterns including synchronous/asynchronous communication, GET/POST requests, and data transmission in Form URL-encoded/JSON formats.

## Key Features

- **HTTP Interface**: Utilizing Spring Boot 3's declarative HTTP communication functionality
- **Sync/Async Communication**: Support for both `ResponseEntity` and `Mono<ResponseEntity>` patterns
- **Multiple HTTP Methods**: Implementation of GET, POST (Form, JSON) requests
- **Configuration Separation**: Separate concerns for WebClient configuration and API configuration

## Tech Stack

- **Java**: 21
- **Spring Boot**: 3.5.4
- **Spring WebFlux**: Reactive HTTP client
- **Gradle**: 8.14.3
- **Docker Compose**: For launching external API server (httpbin)

## Setup

### Prerequisites

- Java 21 or higher
- Docker & Docker Compose

### Getting Started

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd http-interface-client-etude
   ```

2. **Start the external API server**
   ```bash
   docker-compose up -d
   ```
   
   This will start the httpbin server at `http://localhost:8999`.

3. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

4. **Build**
   ```bash
   ./gradlew build
   ```

## Project Structure

```
src/main/java/com/example/
├── Main.java                    # Application entry point
├── Runner.java                  # Runner for testing HTTP communication
├── WebClientConfiguration.java # WebClient and HttpServiceProxyFactory configuration
└── api/
    ├── ApiConfiguration.java   # API service bean definitions
    ├── AnythingService.java    # HTTP Interface service definitions
    └── dto/
        ├── AnythingRequestDto.java  # Request DTO
        └── AnythingResponseDto.java # Response DTO
```

## HTTP Interface Implementation Examples

### Service Interface

```java
@HttpExchange("/anything")
public interface AnythingService {
    
    // Synchronous GET request
    @GetExchange
    ResponseEntity<AnythingResponseDto> get(
        @RequestParam("param1") String param1,
        @RequestParam("param2") String param2
    );
    
    // Asynchronous POST request (JSON)
    @PostExchange
    Mono<ResponseEntity<AnythingResponseDto>> asyncPostJson(
        @RequestBody AnythingRequestDto request
    );
}
```

### Configuration Classes

WebClient and HttpServiceProxyFactory configuration:

```java
@Configuration
public class WebClientConfiguration {
    
    @Bean
    public WebClient webClient(@Value("${backend.uri}") String baseUri) {
        return WebClient.builder().baseUrl(baseUri).build();
    }
    
    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory(WebClient webClient) {
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        return HttpServiceProxyFactory.builderFor(adapter).build();
    }
}
```

## HTTP Communication Patterns

The following HTTP communication patterns are executed sequentially when the application runs:

1. **Synchronous GET**: GET request with query parameters
2. **Synchronous POST (Form)**: POST request with Form URL-encoded data
3. **Synchronous POST (JSON)**: POST request with JSON body
4. **Asynchronous GET**: Reactive GET request
5. **Asynchronous POST (Form)**: Reactive Form POST request
6. **Asynchronous POST (JSON)**: Reactive JSON POST request

## Configuration

You can modify settings in `src/main/resources/application.properties`:

```properties
backend.protocol=http
backend.host=localhost
backend.port=8999
backend.path=
backend.uri=${backend.protocol}://${backend.host}:${backend.port}${backend.path}
```

## Logging Configuration

The following log levels are configured to view detailed HTTP communication logs:

- `org.springframework.web.reactive=TRACE`: Detailed WebFlux logs
- `com.example=TRACE`: Application logs

## Learning Points

- **Declarative HTTP Communication**: Annotation-based HTTP communication implementation
- **Reactive Programming**: Asynchronous processing using Mono and Flux
- **Configuration Separation**: Dividing configurations based on responsibilities
- **Spring Boot 3**: Utilizing the latest Spring Boot features

## License

Apache License 2.0