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