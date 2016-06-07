#!/usr/bin/env bash
set -ex
java -jar target/dropwizard-scala-example.jar server src/main/resources/config.yml
