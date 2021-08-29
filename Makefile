IMAGE_NAME:=jecklgamis/dropwizard-scala-example
IMAGE_TAG:=$(shell git rev-parse HEAD)
changeNumber:=$(shell git rev-parse HEAD)
buildTime:=$(shell date -u +%Y-%m-%dT%H:%M:%SZ)
buildBranch:=$(shell git rev-parse --abbrev-ref HEAD)
default:
	cat ./Makefile
dist: 
	./mvnw -DchangeNumber=$(changeNumber) -DbuildTime=$(buildTime) -DbuildBranch=$(buildBranch) clean package
image:
	 docker build -t $(IMAGE_NAME):$(IMAGE_TAG) -t $(IMAGE_NAME):latest .
run:
	 docker run -p 8080:8080 -p 8081:8081 -p 8443:8443 $(IMAGE_NAME):$(IMAGE_TAG)
run-bash:
	 docker run -it $(IMAGE_NAME):$(IMAGE_TAG) /bin/bash
keystore:
	 ./generate-keystore.sh
up: keystore dist image run
push:
	 docker push $(IMAGE_NAME):$(IMAGE_TAG)
	 docker push $(IMAGE_NAME):latest
tag:
	 git tag -m "dropwizard-kotlin-version-v$(IMAGE_TAG)" -a "v$(IMAGE_TAG)"
	 git push --tags
release-it: dist image push
	cd deployment/k8s && ./create-k8s-files.py --version $(IMAGE_TAG)
	kubectl apply -f deployment/k8s/deployment-$(IMAGE_TAG).yaml


