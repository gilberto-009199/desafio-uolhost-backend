#!/bin/sh

# AZURE LIXO!!!

chmod +x mvnw

./mvnw spring-boot:run \
  -Dspring-boot.run.arguments="--server.port=${PORT}"
