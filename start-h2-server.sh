#!/usr/bin/env bash
# Starts a standalone H2 TCP server for custom datasource exercises.
# Data is stored in ./h2-data/ (persists between restarts).
#
# The H2 jar is resolved from the version declared in pom.xml (single source
# of truth) via Maven's dependency:build-classpath — no hardcoded version here.
#
# Connect from your app with:
#   spring.datasource.url=jdbc:h2:tcp://localhost:9092/./pizzadb
#   spring.datasource.driver-class-name=org.h2.Driver
#   spring.datasource.username=sa
#   spring.datasource.password=
#
# H2 web console: http://localhost:8082
#   JDBC URL to paste there: jdbc:h2:tcp://localhost:9092/./pizzadb

set -euo pipefail
cd "$(dirname "$0")"

echo "Resolving H2 jar from pom.xml..."
H2_JAR="$(./mvnw -q dependency:build-classpath \
  -Dmdep.includeArtifactIds=h2 \
  -Dmdep.outputFile=/dev/stdout 2>/dev/null | tr ':' '\n' | grep -F '/h2/' || true)"

if [ -z "$H2_JAR" ] || [ ! -f "$H2_JAR" ]; then
  echo "Could not resolve the H2 jar."
  echo "Run './mvnw dependency:resolve' first to populate the local Maven cache."
  exit 1
fi
echo "  Using: $H2_JAR"

DATA_DIR="$(pwd)/h2-data"
mkdir -p "$DATA_DIR"

echo "Starting H2 TCP server..."
echo "  TCP:  jdbc:h2:tcp://localhost:9092/./pizzadb"
echo "  Web console: http://localhost:8082"
echo "  Data directory: $DATA_DIR"
echo ""
echo "Press Ctrl+C to stop."
echo ""

java -cp "$H2_JAR" org.h2.tools.Server \
  -tcp -tcpPort 9092 -tcpAllowOthers \
  -web -webPort 8082 \
  -baseDir "$DATA_DIR" \
  -ifNotExists
