# User Window Project

The project is about Sign In and Create New User windows

The application allows users access to the chatting window after signing in through a locally hosted server. 

Applying JavaFX libraries

- GUI - graphical user interface;
 
- File input/output (save user's information to users.txt and use it to sign in);
 
- Socket input/output (connect Server and User);
 
- Scenebuilder (fxml);
 
- Multithreading
 
into the project.

# Instruction

- Download the reposity with master branch (Clone or Download -> Download ZIP)

- Extract file and open it as a new package in Eclipse/IntelliJ IDEA

- Run the server.java file first, then run the user.java

- File build.gradle must be saved as paralled as 'src' (build.gradle and src are under the same directory)

```
apply plugin: 'application'

apply plugin: 'java'
 
repositories {
    mavenCentral()
}
 
dependencies {
    compile 'log4j:log4j:1.2.17'
    compile project(':User')
}
 
mainClassName = 'net.petrikainulainen.gradle.User.user.Server'
 
task copyLicense {
    outputs.file new File("$buildDir/LICENSE")
    doLast {
        copy {
            from "LICENSE"
            into "$buildDir"
        }
    }
}
 
applicationDistribution.from(copyLicense) {
    into ""
}
```   
