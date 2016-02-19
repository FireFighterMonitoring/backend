#!/usr/bin/env bash

# exit on first error
set -e

MY_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

MODULE_NAME="$(grep group "$MY_DIR"/gradle.properties | cut -d = -f 2).$(grep rootProject.name "$MY_DIR"/settings.gradle | cut -d \' -f 2)"
CURRENT_VERSION="$(grep version "$MY_DIR"/gradle.properties | cut -d = -f 2)"
APP_JAR="$MY_DIR/build/libs/$MODULE_NAME-$CURRENT_VERSION.jar"

if [ ! -f "$APP_JAR" ]; then
  echo "Cannot find '$APP_JAR' file - building"
  "$MY_DIR/gradlew" -p "$MY_DIR" clean assemble
  echo
fi

LOCAL_CONFIG="$MY_DIR/application-local.properties"
if [ -f "$LOCAL_CONFIG" ]; then
  echo "Found $LOCAL_CONFIG"
  SPRING_ARGS="--spring.config.location=$LOCAL_CONFIG"
fi 

java -jar "$APP_JAR" "$SPRING_ARGS" "$@"
