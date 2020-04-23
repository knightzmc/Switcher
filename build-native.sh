./gradlew clean shadowJar
native-image --report-unsupported-elements-at-runtime -jar build/libs/Switcher.jar Switcher --no-server
