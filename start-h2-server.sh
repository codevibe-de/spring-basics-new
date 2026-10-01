#!/usr/bin/env bash
# Starts a standalone H2 TCP server using the JVM and it's own runtime classpath.
# Data is stored in ./h2-data/ (persists between restarts).
#

set -euo pipefail
cd "$(dirname "$0")"

echo "Building project classpath..."
CLASSPATH="$(./mvnw -q dependency:build-classpath \
  -Dmdep.outputFile=/dev/stdout 2>/dev/null || true)"

if [ -z "$CLASSPATH" ]; then
  echo "Could not build the project classpath."
  echo "Run './mvnw dependency:resolve' first to populate the local Maven cache."
  exit 1
fi

DATA_DIR="$(pwd)/h2-data"
mkdir -p "$DATA_DIR"

echo "Starting H2 TCP server (from app classpath)..."
echo "  TCP:  jdbc:h2:tcp://localhost:9092/./pizzadb"
echo "  Web console: http://localhost:8082"
echo "  Data directory: $DATA_DIR"
echo ""
echo "Press Ctrl+C to stop."
echo ""

java -cp "$CLASSPATH" org.h2.tools.Server \
  -tcp -tcpPort 9092 -tcpAllowOthers \
  -web -webPort 8082 \
  -baseDir "$DATA_DIR" \
  -ifNotExists
