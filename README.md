
# Jenkins Phone_Book_App CI/CD Pipeline



This repository contains the source code and CI/CD pipeline for the REPtel application developed in JavaFX with MySQL. The CI/CD pipeline is implemented using Jenkins and includes stages such as Git Checkout, Compile, SonarQube Analysis, OWASP SCAN, Build Application, Build & Push Docker Image, and Trigger CD Pipeline.


## Project Structure
- `src/`: Contains the source code for the REPtel application.
- `Dockerfile`: Dockerfile for building a Docker image of the REPtel application.
- `Jenkinsfile`: Defines the CI/CD pipeline stages for Jenkins.

## Prerequisites

Before running the CI/CD pipeline, make sure you have the following tools installed:

- Java Development Kit (JDK 17)
- Docker
- SonarQube Scanner


## Setting up Jenkins

1. Install Jenkins on your server or local machine.
```bash 
wget -q -O - https://pkg.jenkins.io/debian/jenkins.io.key | sudo apt-key add -
sudo sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
sudo apt-get update
sudo apt-get install -y jenkins 
```


2. Configure Jenkins with the necessary plugins for Git, Docker, and SonarQube.
```bash 
java -jar jenkins-cli.jar -s http://localhost:8080/ install-plugin git docker sonar -restart
```
3. Create a Jenkins pipeline job and link it to this repository.
```bash 
java -jar jenkins-cli.jar -s http://localhost:8080/ build RepertoireTelephonique-CI-CD
```
## Pipeline Stages

1. **Git Checkout**: Fetches the latest source code from the repository.
2. **Print Directory Contents Before Compile**: Prints directory contents before the compilation stage.
3. **Compile**: Compiles the Java source code and stores the classes in the `target` directory.
4. **Print Directory Contents After Compile**: Prints directory contents after the compilation stage.
5. **SonarQube Analysis**: Performs static code analysis using SonarQube.
6. **OWASP SCAN**: Conducts a security scan using OWASP Dependency-Check.
7. **Build Application**: Creates a JAR file for the REPtel application.
8. **Build & Push Docker Image**: Builds a Docker image and pushes it to Docker Hub.
9. **Trigger CD Pipeline**: Triggers the downstream CD pipeline.


[](https://asset.cloudinary.com/drgzrvosx/7febc64c1a650866d0378ab155fd2d53)


![](https://res.cloudinary.com/drgzrvosx/image/upload/v1706781291/Screenshot_from_2024-01-31_23-59-09_baqd22.png)
## Running the Pipeline

To run the CI/CD pipeline manually, execute the Jenkins pipeline job.

## License

This project is licensed under the [MIT License](LICENSE).

Feel free to contribute and open issues for any improvements or bugs.


