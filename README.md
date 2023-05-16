## Dropwizard Scala Example

[![Build](https://github.com/jecklgamis/dropwizard-scala-example/actions/workflows/build.yml/badge.svg)](https://github.com/jecklgamis/dropwizard-scala-example/actions/workflows/build.yml)

Docker: `docker run -p 8080:8080 -it jecklgamis/dropwizard-scala-example:main`

This is an example Dropwizard app using Scala.

## What's In The Box?

* Dropwizard 4
* Scala 2.13
* Kubernetes deployment

## Running The App

Ensure you have Java 11 or later.

```
mvn clean package
java -jar target/dropwizard-scala-example.jar
```

## Running The App Using Docker

Ensure you have a working Docker environment.

```
make dist image run
```

## Endpoints

```
curl -v  http://localhost:8080/
curl -v -k https://localhost:8081/
```

## Deploying To Kubernetes

Assumptions:

* You have `helm`command installed (Mac OS: `brew install helm`)
* You can deploy to a Kubernetes cluster that can access Docker Hub

Build and install Helm chart:

```bash
cd deployment/k8s/helm
make install 
```

This creates:

* a service account
* a pod running on port 8080
* a service listening on port 80
* a deployment
* a ingress

To connect to the app locally, create a tunnel to the service:

```bash
kubectl port-forward service/dropwizard-scala-example 80:80
curl http://localhost:80
```

If you have ingress controller installed in your cluster, you can connect using

````
curl -v -H "Host:dropwizard-scala-example.local" https://<your-ingress-load-balancer-hostname>
````

## Contributing

Please raise issue or pull request! Thanks!


