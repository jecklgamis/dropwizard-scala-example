default:
	cat ./Makefile
dist:
	./mvnw clean install
image:
	docker build -t dropwizard-scala-example .
run:
	docker run -p 8080:8080 -p 8081:8081 -p 8443:8443 dropwizard-scala-example
run-bash:
	docker run -i -t dropwizard-scala-example /bin/bash
up: dist image run
keystore:
	 ./generate-keystore.sh
