pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository
                checkout scm
            }
        }

        stage('Build Eureka Server') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Deploy Eureka') {
            steps {                
                // Copy the built .jar file to the EC2 instance
                sh ' scp -i /var/jenkins_home/.ssh/id_rsa /var/jenkins_home/workspace/demo-pipeline/target/EurekaServer.jar root@192.168.1.9:~/'
                sh ' scp -i /var/jenkins_home/.ssh/id_rsa /var/jenkins_home/workspace/demo-pipeline/Dockerfile root@192.168.1.9:~/'
                
                // SSH into the EC2 instance and deploy the .jar in Docker
                sh "ssh root@192.168.1.9 'docker stop EurekaServer || true'"
                sh "ssh root@192.168.1.9 'docker rm EurekaServer || true'"
                sh "ssh root@192.168.1.9 'docker rmi EurekaServer || true'"
                sh "ssh root@192.168.1.9 'docker build -t EurekaServer .'"
                sh "ssh root@192.168.1.9 'docker run -it -d -p 8761:8761 --name=EurekaServer EurekaServer'"
            }
        }
    }
}
