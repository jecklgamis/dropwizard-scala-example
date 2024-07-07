## Dropwizard Scala Example

[![Build](https://github.com/jecklgamis/dropwizard-scala-example/actions/workflows/build.yml/badge.svg)](https://github.com/jecklgamis/dropwizard-scala-example/actions/workflows/build.yml)

Docker: `docker run -p 8080:8080 -p 8081:8081 -it jecklgamis/dropwizard-scala-example:main`

This is an example Dropwizard app using Scala.

## What's In The Box?

* JDK 21
* Dropwizard 5.x
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
make up
```

## Endpoints

```
curl -v http://localhost:8080/
curl -v http://localhost:8081/
```

## Deploying To Kubernetes

Ensure:

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
* an ingress 

To connect to the app locally, create a tunnel to the service:

```bash
kubectl port-forward service/dropwizard-scala-example 8080:80
curl http://localhost:8080
```

If you have ingress controller installed in your cluster, you can connect using

````
curl -k -v -H "Host:dropwizard-scala-example.local" https://<your-ingress-load-balancer-dns>
````

## Contributing

Please raise issue or pull request! Thanks!


