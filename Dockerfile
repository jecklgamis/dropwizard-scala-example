FROM jecklgamis/ubuntu-14.04:latest
MAINTAINER Jerrico Gamis <jecklgamis@gmail.com>

RUN groupadd -r apprunner && useradd -r -gapprunner apprunner
RUN mkdir -m 0755 -p /usr/local/app/bin
RUN mkdir -m 0755 -p /usr/local/app/config

COPY target/dropwizard-scala-example.jar /usr/local/app/bin
COPY start.sh /usr/local/app/bin
COPY src/main/resources/config.yml /usr/local/app/config

RUN chown -R apprunner:apprunner /usr/local/app
RUN chmod +x /usr/local/app/bin/start.sh

USER apprunner
ENTRYPOINT ["/usr/local/app/bin/start.sh"]
CMD ["app"]

