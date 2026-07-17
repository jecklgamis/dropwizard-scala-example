# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Build fat JAR
./mvnw clean package

# Run the app (requires built JAR)
java -jar target/dropwizard-scala-example.jar

# Run tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=AppIntSpec

# Build Docker image and run with Docker Compose
make up

# Build Docker image only
make image
```

The app serves on port 8080 (application) and 8081 (Dropwizard admin).

## Architecture

This is a Dropwizard 5.x REST API written in Scala 3 on JDK 25.

**Entry point**: `App.scala` extends `io.dropwizard.core.Application[AppConfig]`. The `run()` method registers all Jersey resources, filters, and health checks with the Dropwizard `Environment`.

**Configuration**: `AppConfig.scala` extends Dropwizard `Configuration`. App is started with `config.yaml` (in `src/main/resources/`), which sets ports and log level.

**JSON serialization**: `CustomJacksonJaxbJsonProvider` registers `jackson-module-scala` and Java Time modules with Jackson. All JAX-RS resources return Scala case classes or Java objects serialized to JSON.

**Request tracing**: `DiagnosticContextFilter` is a Jersey filter that injects a unique request ID into the SLF4J MDC on each request and removes it on response.

**Testing**: Integration tests use `DropwizardTestSupport` from `dropwizard-testing`. The `AppTestSupport` trait provides a `withAppRunning()` helper that starts the app on random ports (via `PortUtil`) and tears it down after the test block. Test support instances are cached by config path to avoid redundant startups.

## Package Structure

```
dropwizard.scala.example
├── App.scala                        # Main application class
├── AppConfig.scala                  # Configuration bean
├── CustomJacksonJaxbJsonProvider.scala
├── resource/
│   ├── RootResource.scala           # GET /
│   └── ProbeResource.scala          # GET /probe/live, GET /probe/ready
├── filter/
│   └── DiagnosticContextFilter.scala
└── health/
    └── DefaultHealthCheck.scala
```

Tests are under `src/test/scala/dropwizard/scala/example/`, with test utilities in the `test.util` sub-package.

## Adding New Endpoints

1. Create a new resource class in `resource/` using JAX-RS annotations (`@Path`, `@GET`, etc.)
2. Register it in `App.run()` via `env.jersey().register(...)`
3. Add an integration test in `AppIntSpec` or a new spec using `AppTestSupport`
