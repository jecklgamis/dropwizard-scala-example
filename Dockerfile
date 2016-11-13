FROM jecklgamis/java-runtime:latest
MAINTAINER Jerrico Gamis <jecklgamis@gmail.com>

RUN apt-get update -y && apt-get install -y supervisor
RUN groupadd -r app && useradd -r -gapp app
RUN mkdir -m 0755 -p /usr/local/app/bin
RUN mkdir -m 0755 -p /usr/local/app/config

COPY target/dropwizard-scala-example.jar /usr/local/app/bin
COPY start.sh /usr/local/app/bin
COPY src/main/resources/config.yml /usr/local/app/config

COPY app-supervisor.conf /etc/supervisor/conf.d

RUN chown -R app:app /usr/local/app
RUN chmod +x /usr/local/app/bin/start.sh

EXPOSE 8080
EXPOSE 8081

CMD ["/usr/bin/supervisord"]

