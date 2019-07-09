# Automated-Parking-System
Automated ticketing system that allows customers to use parking lot without human intervention.

The system allow input in two ways.

1) An interactive command prompt based shell where commands can be typed in.
2) Accept a filename as a parameter at the command prompt and read the commands from that file.

## For Developers

Please refer to the README.md in each subsequent folder for development instructions

## Running the entire application on local machine

### Prerequisites

- Git ([OSX](https://git-scm.com/download/mac)) ([Windows](https://git-scm.com/download/win))
  ([Linux](https://git-scm.com/download/linux))
- Java 8 installed (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
    - If you have previously had Java 9 installed, change the JAVA_HOME path to where Java 1.8 is
        - e.g. for a Mac, you can run the following after install
        ```
            $ export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
        ```
- Maven 3.3.9+ installed (https://maven.apache.org/install.html)

### Clone the repository

```
    $ git clone https://github.com/mahar89/Automated-Parking-System.git
```

### Build the application
1.Build Automated-Parking-System

```
    $ cd /path/to/Automated-Parking-System
    $ mvn clean install
```

3. Run the app:

    #### Through IntelliJ / Eclipse

     i. You can open up the project in IntelliJ / eclipse, and run the application with the
     following parameters:
     ```
       Main class: `ParkingSystemApp` 
       Optional Program Arguments: 'Path to input file containing commands'
     ```
     
    
     