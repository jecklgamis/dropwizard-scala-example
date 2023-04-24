IMAGE_NAME:=jecklgamis/dropwizard-scala-example
IMAGE_TAG:=main

default:
	cat ./Makefile
dist:
	./mvnw clean package
image:
	docker build -t $(IMAGE_NAME):$(IMAGE_TAG) .
run:
	docker run -p 8080:8080  -p 8443:8443 $(IMAGE_NAME):$(IMAGE_TAG)
run-bash:
	docker run -i -t $(IMAGE_NAME):$(IMAGE_TAG) /bin/bash
chart:
	cd deployment/k8s/helm && make package
all: dist image
up: all run