## Dropwizard Scala Example

[![Build Status](https://travis-ci.org/jecklgamis/dropwizard-scala-example.svg?branch=master)](https://travis-ci.org/jecklgamis/dropwizard-scala-example)

This is an example Dropwizard app using Scala. 

## Running The App 
Ensure you have Java 8 or later.
```
mvn clean package
java -jar target/dropwizard-scala-example.jar
```

## Running The App Using Docker
Ensure you have a working Docker environment.
```
make dist image run
```

## Testing The Endpoints
Point your browser to `http://localhost:8080` or use `curl` in command line.

```
curl -v  http://localhost:8080/
curl -v -k https://localhost:8443/
```
Operational menu endpoint:
* `http://localhost:8081`


