# Simple Calculator Application

** This project is for educational purposes only **

** This project is based on https://github.com/H4rryp0tt3r/Calculator by Nageswara Rao Podilapu **

## PlantUML dependency on Graphviz

To properly work on all types of diagrams PlantUML depends on Graphviz. This is something that the gradle build does not solve automatically.

Please refer to page [plantuml-graphviz-dot](http://plantuml.com/graphviz-dot) for further information on this issue and on instructions to install Graphviz on your system.

## Installation

The following commands are for ubuntu OS.
```
sudo apt-get update; sudo apt-get install -y java-11-amazon-corretto-jdk

sudo update-alternatives --config java

sudo apt-get install -y graphviz
```

## Overview

To fully generate the overview.html it is required to build the project with
```
gradle build
```

## Jenkins Plugins

* AdoptOpenJDK installer Plugin
* [Gradle Plugin](https://plugins.jenkins.io/gradle/)
* [JaCoCo Plugin](https://plugins.jenkins.io/jacoco/)
* [Javadoc plugin](https://plugins.jenkins.io/javadoc/)
    - It may be needed to do the [following](https://stackoverflow.com/a/46197356/9915287) to allow access from jenkins to the html content