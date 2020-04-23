./gradlew clean shadowJar
native-image --report-unsupported-elements-at-runtime -jar build/libs/switcher.jar build/libs/switcher --no-server
