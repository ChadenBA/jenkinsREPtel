//ci pipeline
pipeline {
    agent any
    tools {
        jdk 'jdk17'
        dockerTool 'docker'
    }

    
    environment {
        SCANNER_HOME = tool 'sonar-scanner'
    }
 
    stages {
        stage('Git checkout') {
            steps {
               git url: 'https://github.com/ChadenBA/jenkinsREPtel.git'
            }
        }
        
stage('Print Directory Contents Before Compile') {
    steps {
        sh 'ls -R'
    }
}
stage('Compile') {
    steps {
        sh "javac -d ./target/classes ./src/Main.java"
    }
}


stage('Print Directory Contents After Compile') {
    steps {
        sh 'ls -R'
    }
}





stage('SonarQube analysis') {
    steps {
        sh """$SCANNER_HOME/bin/sonar-scanner \
           -Dsonar.host.url=http://localhost:9000/ \
           -Dsonar.login=squ_6cde802303373d5694263de9af7d97c7eafdba31 \
           -Dsonar.projectName=reptel \
           -Dsonar.projectKey=reptel \
           -Dsonar.java.binaries=./target/classes
        """
    }
}

stage('OWASP SCAN') {
    steps {
        dependencyCheck additionalArguments: '...scan ./', odcInstallation: 'DP'
        dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
    }
}


stage('Build Application') {
            steps {
                sh "jar cf ./target/repTel.jar -C ./target/classes ."
            }
        }


stage('Build & Push Docker Image') {
    steps {
        script {
            withDockerRegistry(credentialsId: '4376e9ed-1a1f-4e86-88dd-7d8e5ff95d9d', toolName: 'docker') {
             sh "docker build -t reptel:latest -f /var/lib/jenkins/workspace/ciPipeline/Dockerfile ."

                sh "docker tag reptel:latest chaden13/reptel:latest"
                sh "docker push chaden13/reptel:latest"
            }
        }
    }
}

stage('Trigger CD Pipleline') {
    steps {
        build job: "Cd_pipline" , wait: true 
    }
}



}

}

//cd pipeline
pipeline {
    agent any

    stages {
                stage('Docker Deploy to container') {
            steps {
                script {
                    withDockerRegistry(credentialsId: '4376e9ed-1a1f-4e86-88dd-7d8e5ff95d9d', toolName: 'docker') {
                       sh "docker run -d --name reptel-conteneur -p 8009:8009 chaden13/reptel:latest"
                    }
                }
            }
        }
            }
        
}
