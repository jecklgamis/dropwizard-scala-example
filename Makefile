default:
	cat ./Makefile
dist: jar docker-image
	@echo All done
jar:
	mvn clean package
docker-image:
	docker build -t dropwizard-scala-example .
docker-run:
	docker run -p 8080:8080 dropwizard-scala-example
docker-run-bash:
	docker run -i -t dropwizard-scala-example /bin/bash
