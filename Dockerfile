FROM ubuntu:24.04
LABEL org.opencontainers.image.authors="jecklgamis@gmail.com"

RUN apt update -y && apt install -y openjdk-21-jre-headless && rm -rf /var/lib/apt/lists/*

ENV APP_HOME=/app

RUN groupadd -r app && useradd -r -gapp app
RUN mkdir -m 0755 -p $APP_HOME/bin
RUN mkdir -m 0755 -p $APP_HOME/config
RUN mkdir -m 0755 -p $APP_HOME/logs

COPY target/dropwizard-scala-example.jar $APP_HOME/bin
COPY src/main/resources/config.yaml $APP_HOME/config

RUN chown -R app:app $APP_HOME
WORKDIR $APP_HOME
USER app

COPY docker-entrypoint.sh /
ENTRYPOINT ["/docker-entrypoint.sh"]

