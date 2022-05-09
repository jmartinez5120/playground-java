# Gradle Notes

## Advantages over Ant or Maven
* Is a structural and organazed as Maven. Folder structure is the same. Convention over configuration.
* It can be customized as Ant.
* SUPER fast, because it keeps track of the changes done in the files, to only compile those and then ones that are dependent to those changes.
  * Daemon Process
  * Incremental builds
  * Keeps build cache
* Uses Groovy or Kotlin
* Gradle Wrapper lets other users use gradle without installing in their machines. Most IDEs will pickup the wrapper.

## Starting a new Project
You can start a new project using `gradle init` which will ask you a series of questions to create all the initial files required by a gradle project.

## Gradle Tasks
The following task will print a String when executed. To execute, you can use the following command: `gradle task firstTask` or `gradle task fT` 

*Note: fT is the abreviated form of firstTask*

``` groovy
task firstTask {
    println 'Gradle doesnt Rock!!!'
}
```

You can view all the available tasks with the following command:
`gradle task` or `gradle task --all`

## Gradle Java
You can use gradle init and choose option `#2` and follow the Java options to create a application, either multiple module or single module.

**IMPORTANT:** When gradle is installed, it doesn't know how to build any project. All the tasks comes from the plugins. For example:
``` groovy
plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    id 'application'
}
```
The above plugin will contain all the gradle tasks to build and compile a Java project.

```
gradle compileJava -> will compile our Java project and create a build folder with all the compiled code.

gradle test -> will perform all unit and integration test and will create a HTML file with the results of all the tests.

gradle clean -> will delete the build folder to create a brand new one with changes in the code.

gradle jar -> creates a jar file under build/libs/***.jar

gradle run -> runs the project
```

### Main class
To specify your main class in your project, you can use the following:
``` groovy
application {
    // Define the main class for the application.
    mainClass = 'com.playground.gradle.sampleapplication.App'
}
```

## Dependencies
As in Maven, Gradle uses the sections to specify a dependency: group id, artifact and version. In Gradle this is represented as the following:
`<GroupId>:<Artifact>:<Version>` 

- For example: `com.google.guava:guava:30.1.1-jre`

You can use a shortcut to access Maven Central:
``` groovy

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    // Will look for the dependecies under the .m2 folder.
    mavenLocal()
}
```

## Configurations (Scopes in Maven)

The below configurations comes from the Java plugin in the plugins section.
* **Implementation** - the dependency will be present at all times, compile, runtime, test, etc. Also when copile, it will be included in the WAR file if is a webapp.
* **compileOnly** - available only during compile. *Example: lombok, JMapper, Dozer*
* **runtimeOnly** - available only at runtime. *Example: Logging implementation*
* **testImplmentation** - will be available during test compile and test runtime.
* **testCompileOnly** - available only at test compile time. *Example: Junit, Mockito*
* **testRuntimeOnly** -  available only at test runtime. *Example: Jupiter*
* **api (used to be compile)** - transient dependencies will be available to the project that is using a dependency market with api.

## Gradle Phases
There are three phases.
* Intialization - figures out if is a single project or a multi-module project, creates the build folder with all the required elements
* Configuration - Detects all the tasks that needs to run. Will also detects if there is a circular task or dependencies, to fail. 
* Execution - runs the tasks, test and Jar is builded

*Task example: you can execute the below using: `gradle fT` or `gradle firstTask`.*
``` groovy
// This line will be executed at the Configuration phase
println 'Start initialization...'
task firstTask {
    // This line will execute only at the configuration phase... Anything outside doFirst() and doLast() will execute
    // at the configuration phase.
    println 'Gradle doesnt Rock!!!'

    // This task will be executed at the Execution phase
    doFirst() {
        println 'doFirst print.'
    }
    // This task will be executed at the Execution phase
    doLast() {
        println 'doLast print'
    }
}
// This line will be executed at the Configuration phase
println 'End initialization...'
```