# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java Spring Boot application demonstrating HTTP Interface Client usage with WebClient. It's a study project (etude) that showcases both synchronous and asynchronous HTTP calls using Spring's declarative HTTP interface approach.

## Architecture

- **Main Application**: Spring Boot console application that runs and exits (`Main.java:26`)
- **HTTP Service Layer**: Declarative HTTP interface using `@HttpExchange` annotations (`AnythingService.java:30`)
- **Configuration**: WebClient-based HTTP service proxy factory configuration (`ApiConfiguration.java:28`)
- **DTOs**: Request/response data transfer objects using Java records
- **External Service**: Uses httpbin service running on Docker for testing HTTP calls

The application demonstrates different HTTP call patterns:
- Synchronous GET/POST calls returning `ResponseEntity<T>`
- Asynchronous GET/POST calls returning `Mono<ResponseEntity<T>>`
- Form URL-encoded and JSON content types

## Development Commands

### Build and Run
```bash
./gradlew build
./gradlew bootRun
```

### Start External Dependencies
```bash
docker-compose up -d
```

### Clean Build
```bash
./gradlew clean build
```

## Configuration

- Backend service configuration is in `application.properties:14` using property interpolation
- Default backend points to httpbin service at `http://localhost:8999`
- Logging is configured for detailed Spring WebFlux tracing
- Application runs as console app (not web server) via `spring.main.web-application-type=none`

## Key Components

- `Runner.java:39`: ApplicationRunner that executes all HTTP operations on startup
- `AnythingService.java`: HTTP interface defining both sync and async operations
- `ApiConfiguration.java:31`: Creates HTTP service proxy using WebClient adapter
- DTOs in `api/dto/`: Java records for request/response mapping

## Development Notes

- Uses Java 21 with Spring Boot 3.4.2
- WebFlux stack for reactive HTTP clients
- No web server - runs as command-line application
- External httpbin service required for testing
