#!/usr/bin/env bash

# exit on first error
set -e

MY_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

MODULE_NAME="$(grep group "$MY_DIR"/gradle.properties | cut -d = -f 2).rest"
CURRENT_VERSION="$(grep version "$MY_DIR"/gradle.properties | cut -d = -f 2)"
APP_JAR="$MY_DIR/rest/build/libs/$MODULE_NAME-$CURRENT_VERSION.jar"
LOCAL_CONFIG="$MY_DIR/application-local.properties"

if [ ! -f "$APP_JAR" ]; then
  echo "Cannot find '$APP_JAR' file - building"
  "$MY_DIR/gradlew" -p "$MY_DIR" :rest:assemble
  echo
fi

if [ ! -f "$LOCAL_CONFIG" ]; then
  echo "Cannot find '$LOCAL_CONFIG' file - it is necessary to configure the required services."
  exit 1
fi

java -jar "$APP_JAR" --spring.config.location="$LOCAL_CONFIG" "$@"
