./gradlew clean shadowJar
native-image --report-unsupported-elements-at-runtime -jar build/libs/Switcher.jar switcher --no-server
