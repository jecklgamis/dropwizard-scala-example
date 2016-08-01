default:
	cat ./Makefile
dist: jar docker-image
	@echo All done
jar:
	mvn clean package
docker-image:
	docker build -t dropwizard-scala-example .
docker-run:
	docker run dropwizard-scala-example
docker-run-bash:
	docker run -i -t dropwizard-scala-example /bin/bash
