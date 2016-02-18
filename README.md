[![Build Status](https://api.travis-ci.org/FireFighterMonitoring/backend.png)](https://travis-ci.org/FireFighterMonitoring/backend)

To build the whole project after checkout:

    ./gradlew # linux, osx
    ./gradlew.bat # windows

This will download all necessary dependencies, including Gradle itself, and perform a full clean build, including quality checks like tests and static code analysis.

To compile and package the modules without any quality checks:

    ./gradlew -x check

Run the application in exploded form:

    ./gradlew bootRun

Run the application in packaged form:

    ./gradlew clean && ./run.sh
