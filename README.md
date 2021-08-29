## Dropwizard Scala Example

[![Build](https://github.com/jecklgamis/dropwizard-scala-example/actions/workflows/build.yml/badge.svg)](https://github.com/jecklgamis/dropwizard-scala-example/actions/workflows/build.yml)


This is an example Dropwizard app using Scala. 

## Running The App 
Ensure you have Java 8 or later.
```
./generate-keystore.sh
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


