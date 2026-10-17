#!/bin/sh

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

echo "Executando a partir de: $SCRIPT_DIR"

chmod +x "$SCRIPT_DIR/mvnw"

"$SCRIPT_DIR/mvnw" spring-boot:run \
  -Dspring-boot.run.arguments="--server.port=${PORT}"
