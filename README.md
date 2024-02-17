# Courgette-JVM with Appium (iOS)

An example project showing how to use Courgette-JVM with Appium to test iOS native applications.

## System Requirements

* Java
* MacOS
* [Appium](https://appium.io/docs/en/about-appium/getting-started/?lang=en)
* [XCode](https://developer.apple.com/xcode/) with iPhone 15, iPhone 15 Plus and iPhone 15 Pro simulators

## Test Execution

https://user-images.githubusercontent.com/2563149/144464468-91b044bd-52ee-4cc3-abb8-0301762a9942.mp4


Using Gradle from the command line

````gradle
./gradlew runIosTestsInParallel
````

Using JUnit in the IDE
````java
runners/IosTestRunner.java
````
