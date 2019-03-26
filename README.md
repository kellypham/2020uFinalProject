CSCI2020u FinalProject - Group 17

1. Group Members:
- Manmeet Singh Choudhary (mannyc101)
- Talha Rashid (TalhaRashid922)
- Phuong Pham (kellypham)

Contributions: 
The project is about Sign In and Create New User windows. After you sign in, you can access to the chatting. The contributions are seperated to all members. Manmeet did the sophisticated user interface and file input and output (save user's information to users.txt and use it to sign in). Talha did the socket input and output (connect Server and User) and scenebuilder (fxml). Phuong made the multithreading (chatting) and README.md file. We together editted and debugged code.

2. The repository URL: https://github.com/kellypham/2020uFinalProject

3. Instructions:
- Download the 2020uFinalProject reposity with master branch (Clone or Download -> Download ZIP)
- Extract file and open it as a new package in Eclipse/IntelliJ IDEA
- Run the server.java file first, then run the user.java

- File build.gradle must be saved as paralled as 'src' (build.gradle and src are under the same directory)

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
    
