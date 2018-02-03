default:
	cat ./Makefile
dist:
	mvn clean package
image:
	docker build -t dropwizard-scala-example:latest .
run:
	docker run -p 8080:8080 -p 8081:8081 -p 8443:8443 dropwizard-scala-example:latest
run-bash:
	docker run -i -t dropwizard-scala-example:latest /bin/bash
