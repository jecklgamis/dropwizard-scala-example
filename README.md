## Dropwizard Scala Example

[![Build Status](https://travis-ci.org/jecklgamis/dropwizard-scala-example.svg?branch=master)](https://travis-ci.org/jecklgamis/dropwizard-scala-example)

This is an example Dropwizard app using Scala.

* JSON and XML serialization using Java and Scala entities
* Example resource , health check, filter

## Building The App
Ensure you Java 8 and Maven 3 installed.
```
mvn clean package
```
# Running The App
```
java -jar target/dropwizard-scala-example.jar server src/main/resources/config.yml
```

## Running The App In Docker

```
docker build -t dropwizard-scala-example .
docker run dropwizard-scala-example
```

## XML and JSON Serialization Examples

Example (serialize Java/Scala entity with JAXB annotation to either JSON or XML)
```
curl -v -H "Accept:application/json" http://127.0.0.1:8080/example/scala
curl -v -H "Accept:application/xml" http://127.0.0.1:8080/example/scala
curl -v -H "Accept:application/json" http://127.0.0.1:8080/example/java
curl -v -H "Accept:application/xml" http://127.0.0.1:8080/example/java
```








