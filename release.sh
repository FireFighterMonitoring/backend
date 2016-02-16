#!/usr/bin/env bash

# exit on first error
set -e

function syntax() {
  echo "  ${BASH_SOURCE[0]} [release name] --next [major|minor|patch]"
  exit 1
}

function updateVersion() {
  # poor man's sed -i, but contrary to it, it is portable across linux and osx
  # $1 - current version, $2 - new version
  sed -e "s/$1/$2/" gradle.properties > gradle.properties.updated
  mv gradle.properties.updated gradle.properties
}

function incrementVersion() {
  # $1 - current version, $2 - next release type (major, minor, patch)
  IFS=. read -a v <<< "$1"
  # v is an array: v[0]=major, v[1]=minor, v[2]=patch
  case "$2" in
    major)
      # increment major version, reset minor and patch
      v[0]=$((v[0] + 1))
      v[1]=0
      v[2]=0
      ;;
    minor)
      # increment minor version, reset patch
      v[1]=$((v[1] + 1))
      v[2]=0
      ;;
    patch)
      # increment patch version
      v[2]=$((v[2] + 1))
      ;;
  esac

  echo "${v[0]}.${v[1]}.${v[2]}"
}

function silentGit() {
  # shut up the chatty git, somehow --quiet is unreliable
  git "$@" &>/dev/null
}

MY_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

if [ $# -ne 3 ]; then
  echo "Illegal or missing arguments, syntax:"
  syntax
fi

if [[ ! "$1" =~ ^[a-zA-Z]+$ ]]; then
  echo "Name '$1' contains invalid characters, only ASCII letters are allowed."
  exit 2
fi

NAME="$1"

if [[ "$2" != --next ]]; then
  echo "Unknown switch '$2', syntax:"
  syntax
fi

case "$3" in
  major|minor|patch)
    NEXT_RELEASE_TYPE="$3"
    ;;
  *)
    echo "Invalid next release type '$3', syntax:"
    syntax
esac

cd "$MY_DIR"

if ! silentGit diff-index HEAD; then
  echo "Repository contains uncommitted changes, aborting."
  exit 3
fi

CURRENT_VERSION="$(grep version gradle.properties | cut -d = -f 2)"

if [[ ! "$CURRENT_VERSION" == *-SNAPSHOT ]]; then
  echo "Current version '$CURRENT_VERSION' is not a SNAPSHOT version, don't know what to do."
  exit 4
fi

RELEASE_VERSION="${CURRENT_VERSION%-SNAPSHOT}"
TAG_NAME="$RELEASE_VERSION-$NAME"
BRANCH_NAME="release-$TAG_NAME"

echo "Performing a clean full build on up-to-date 'develop'."
silentGit checkout develop
silentGit pull
./gradlew

echo "Creating release branch '$BRANCH_NAME' off of 'develop'."
silentGit checkout -b "$BRANCH_NAME"

echo "Updating version from '$CURRENT_VERSION' to '$RELEASE_VERSION'."
updateVersion "$CURRENT_VERSION" "$RELEASE_VERSION"
silentGit add gradle.properties
silentGit commit -m 'Update version for release'

echo "Merging release branch to 'master'."
silentGit checkout master
silentGit pull
silentGit merge --no-ff "$BRANCH_NAME" -m 'Merge release branch'

echo "Assembling application."
./gradlew clean :rest:assemble

echo "Creating release tag '$TAG_NAME'."
silentGit tag -a "$TAG_NAME" -m 'Create release tag'

echo "Merging release branch to 'develop'."
silentGit checkout develop
silentGit merge --no-ff "$BRANCH_NAME" -m 'Merge release branch'

echo "Deleting release branch."
silentGit branch -d "$BRANCH_NAME"

NEW_VERSION="$(incrementVersion "$RELEASE_VERSION" "$NEXT_RELEASE_TYPE")-SNAPSHOT"
echo "Updating version from '$RELEASE_VERSION' to '$NEW_VERSION'."
updateVersion "$RELEASE_VERSION" "$NEW_VERSION"
silentGit add gradle.properties
silentGit commit -m 'Update version after release'

while true; do
  read -p "Push changes to origin? [y/N] " ANSWER
  case "$ANSWER" in
    [yY])
      echo "Okidoki."
      break
      ;;
    [nN]|"")
      echo "Ok, not pushing. Local changes are preserved, you will need to push them manually."
      exit
      ;;
    *)
      echo "[y/Y]es or [n/N]o?"
      ;;
  esac
done

echo "Publishing changes."
silentGit checkout master
silentGit push
silentGit push origin "$TAG_NAME"

silentGit checkout develop
silentGit push
