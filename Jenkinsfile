pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Deploy to Docker') {
            steps {                
                // Copy the built .jar file to the EC2 instance
                sh ' scp -i /var/jenkins_home/.ssh/id_rsa /var/jenkins_home/workspace/demo-pipeline/target/demo-2.jar root@192.168.1.9:~/'
                sh ' scp -i /var/jenkins_home/.ssh/id_rsa /var/jenkins_home/workspace/demo-pipeline/Dockerfile root@192.168.1.9:~/'
                
                // SSH into the EC2 instance and deploy the .jar in Docker
                sh "ssh root@192.168.1.9 'docker stop demo-2 || true'"
                sh "ssh root@192.168.1.9 'docker rm demo-2 || true'"
                sh "ssh root@192.168.1.9 'docker rmi demo-2 || true'"
                sh "ssh root@192.168.1.9 'docker build -t demo-2 .'"
                sh "ssh root@192.168.1.9 'docker run -it -d -p 9091:9091 --name=demo-2 demo-2'"
            }
        }
    }
}
