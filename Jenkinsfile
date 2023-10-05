pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository
                checkout scm
            }
        }


         stage('Build Eureka-Server') {
            steps {
                // Build the Spring Boot application using Maven
                sh 'cd Eureka-Server && mvn clean package -DskipTests'
            }
        }

        stage('Deploy Eureka-Server') {
            steps {

                // sh "ssh root@192.168.1.9 'cd /root'"
                sh "ssh root@192.168.1.9 'rm -rf /root/eureka || true'"
                sh "ssh root@192.168.1.9 'mkdir /root/eureka'"
                
                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/Eureka-Server/target/eureka.jar root@192.168.1.9:~/eureka/'
                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/Eureka-Server/Dockerfile root@192.168.1.9:~/eureka/'

                sh "ssh root@192.168.1.9 'docker stop eureka || true'"
                sh "ssh root@192.168.1.9 'docker rm eureka || true'"
                sh "ssh root@192.168.1.9 'docker rmi eureka ||true'"
                sh "ssh root@192.168.1.9 'docker build -t eureka /root/eureka'"
                sh "ssh root@192.168.1.9 'docker run -it -d -p 8761:8761 --name eureka eureka'"
            }
        }        


        stage('Build api-gateway') {
            steps {
                // Build the Spring Boot application using Maven
                sh 'cd api-gateway && mvn clean package -DskipTests'
            }
        }

        stage('Deploy api-gateway') {
            steps {

                // sh "ssh root@192.168.1.9 'cd /root'"
                sh "ssh root@192.168.1.9 'rm -rf /root/api-gateway || true'"
                sh "ssh root@192.168.1.9 'mkdir /root/api-gateway'"
                
                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/api-gateway/target/api-gateway.jar root@192.168.1.9:~/api-gateway/'
                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/api-gateway/Dockerfile root@192.168.1.9:~/api-gateway/'

                sh "ssh root@192.168.1.9 'docker stop api-gateway || true'"
                sh "ssh root@192.168.1.9 'docker rm api-gateway || true'"
                sh "ssh root@192.168.1.9 'docker rmi api-gateway ||true'"
                sh "ssh root@192.168.1.9 'docker build -t api-gateway /root/api-gateway'"
                sh "ssh root@192.168.1.9 'docker run -it -d -p 9090:9090 --name api-gateway api-gateway'"
            }
        }        

        
        stage('Build auth-server') {
            steps {
                // Build the Spring Boot application using Maven
                sh 'cd auth-server && mvn clean package -DskipTests'
            }
        }

        stage('Deploy api-gatewayauth-server') {
            steps {

                // sh "ssh root@192.168.1.9 'cd /root'"
                sh "ssh root@192.168.1.9 'rm -rf /root/auth-server || true'"
                sh "ssh root@192.168.1.9 'mkdir /root/auth-server'"
                
                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/auth-server/target/auth-server.jar root@192.168.1.9:~/auth-server/'
                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/auth-server/Dockerfile root@192.168.1.9:~/auth-server/'

                sh "ssh root@192.168.1.9 'docker stop auth-server || true'"
                sh "ssh root@192.168.1.9 'docker rm auth-server || true'"
                sh "ssh root@192.168.1.9 'docker rmi auth-server ||true'"
                sh "ssh root@192.168.1.9 'docker build -t auth-server /root/auth-server'"
                sh "ssh root@192.168.1.9 'docker run -it -d -p 9090:8083 --name auth-server auth-server'"
            }
        }        

        
        // stage('Build attendance') {
        //     steps {
        //         // Build the Spring Boot application using Maven
        //         sh 'cd attendance && mvn clean package -DskipTests'
        //     }
        // }

        // stage('Deploy attendance') {
        //     steps {

        //         sh "ssh root@192.168.1.9 'cd /root'"
        //         sh "ssh root@192.168.1.9 'rm -rf attendance || true'"
        //         sh "ssh root@192.168.1.9 'mkdir attendance'"
                
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/attendance/target/attendance.jar root@192.168.1.9:~/attendance/'
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/attendance/Dockerfile root@192.168.1.9:~/attendance/'

        //         sh "ssh root@192.168.1.9 'docker stop attendance || true'"
        //         sh "ssh root@192.168.1.9 'docker rm attendance || true'"
        //         sh "ssh root@192.168.1.9 'docker rmi attendance ||true'"
        //         sh "ssh root@192.168.1.9 'docker build -t attendance /root/test-1'"
        //         sh "ssh root@192.168.1.9 'docker run -it -d -p : --name attendance attendance'"
        //     }
        // }        
        

        
        
        // stage('Build broadcast') {
        //     steps {
        //         // Build the Spring Boot application using Maven
        //         sh 'cd broadcast && mvn clean package -DskipTests'
        //     }
        // }

        // stage('Deploy broadcast') {
        //     steps {

        //         sh "ssh root@192.168.1.9 'cd /root'"
        //         sh "ssh root@192.168.1.9 'rm -rf broadcast || true'"
        //         sh "ssh root@192.168.1.9 'mkdir broadcast'"
                
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/broadcast/target/broadcast.jar root@192.168.1.9:~/broadcast/'
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/broadcast/Dockerfile root@192.168.1.9:~/broadcast/'

        //         sh "ssh root@192.168.1.9 'docker stop broadcast || true'"
        //         sh "ssh root@192.168.1.9 'docker rm broadcast || true'"
        //         sh "ssh root@192.168.1.9 'docker rmi broadcast ||true'"
        //         sh "ssh root@192.168.1.9 'docker build -t broadcast /root/test-1'"
        //         sh "ssh root@192.168.1.9 'docker run -it -d -p : --name broadcast broadcast'"
        //     }
        // }        
        
        
        
        // stage('Build community') {
        //     steps {
        //         // Build the Spring Boot application using Maven
        //         sh 'cd community && mvn clean package -DskipTests'
        //     }
        // }

        // stage('Deploy community') {
        //     steps {

        //         sh "ssh root@192.168.1.9 'cd /root'"
        //         sh "ssh root@192.168.1.9 'rm -rf community || true'"
        //         sh "ssh root@192.168.1.9 'mkdir community'"
                
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/community/target/community.jar root@192.168.1.9:~/community/'
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/community/Dockerfile root@192.168.1.9:~/community/'

        //         sh "ssh root@192.168.1.9 'docker stop community || true'"
        //         sh "ssh root@192.168.1.9 'docker rm community || true'"
        //         sh "ssh root@192.168.1.9 'docker rmi community ||true'"
        //         sh "ssh root@192.168.1.9 'docker build -t community /root/test-1'"
        //         sh "ssh root@192.168.1.9 'docker run -it -d -p : --name community community'"
        //     }
        // }        

                
        // stage('Build loginservice') {
        //     steps {
        //         // Build the Spring Boot application using Maven
        //         sh 'cd loginservice && mvn clean package -DskipTests'
        //     }
        // }

        // stage('Deploy loginservice') {
        //     steps {

        //         sh "ssh root@192.168.1.9 'cd /root'"
        //         sh "ssh root@192.168.1.9 'rm -rf loginservice || true'"
        //         sh "ssh root@192.168.1.9 'mkdir loginservice'"
                
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/loginservice/target/loginservice.jar root@192.168.1.9:~/loginservice/'
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/loginservice/Dockerfile root@192.168.1.9:~/loginservice/'

        //         sh "ssh root@192.168.1.9 'docker stop loginservice || true'"
        //         sh "ssh root@192.168.1.9 'docker rm loginservice || true'"
        //         sh "ssh root@192.168.1.9 'docker rmi loginservice ||true'"
        //         sh "ssh root@192.168.1.9 'docker build -t loginservice /root/test-1'"
        //         sh "ssh root@192.168.1.9 'docker run -it -d -p : --name loginservice loginservice'"
        //     }
        // }        

        //         stage('Build loginservice') {
        //     steps {
        //         // Build the Spring Boot application using Maven
        //         sh 'cd loginservice && mvn clean package -DskipTests'
        //     }
        // }

        // stage('Deploy notification') {
        //     steps {

        //         sh "ssh root@192.168.1.9 'cd /root'"
        //         sh "ssh root@192.168.1.9 'rm -rf notification || true'"
        //         sh "ssh root@192.168.1.9 'mkdir notification'"
                
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/notification/target/notification.jar root@192.168.1.9:~/notification/'
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/notification/Dockerfile root@192.168.1.9:~/notification/'

        //         sh "ssh root@192.168.1.9 'docker stop notification || true'"
        //         sh "ssh root@192.168.1.9 'docker rm notification || true'"
        //         sh "ssh root@192.168.1.9 'docker rmi notification ||true'"
        //         sh "ssh root@192.168.1.9 'docker build -t notification /root/test-1'"
        //         sh "ssh root@192.168.1.9 'docker run -it -d -p : --name notification notification'"
        //     }
        // }        

        
        // stage('Build suggestion') {
        //     steps {
        //         // Build the Spring Boot application using Maven
        //         sh 'cd suggestion && mvn clean package -DskipTests'
        //     }
        // }

        // stage('Deploy suggestion') {
        //     steps {

        //         sh "ssh root@192.168.1.9 'cd /root'"
        //         sh "ssh root@192.168.1.9 'rm -rf suggestion || true'"
        //         sh "ssh root@192.168.1.9 'mkdir suggestion'"
                
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/suggestion/target/suggestion.jar root@192.168.1.9:~/suggestion/'
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/suggestion/Dockerfile root@192.168.1.9:~/suggestion/'

        //         sh "ssh root@192.168.1.9 'docker stop suggestion || true'"
        //         sh "ssh root@192.168.1.9 'docker rm suggestion || true'"
        //         sh "ssh root@192.168.1.9 'docker rmi suggestion ||true'"
        //         sh "ssh root@192.168.1.9 'docker build -t suggestion /root/test-1'"
        //         sh "ssh root@192.168.1.9 'docker run -it -d -p : --name suggestion suggestion'"
        //     }
        // }        

        
        
        // stage('Build taskcenter') {
        //     steps {
        //         // Build the Spring Boot application using Maven
        //         sh 'cd taskcenter && mvn clean package -DskipTests'
        //     }
        // }

        // stage('Deploy taskcenter') {
        //     steps {

        //         sh "ssh root@192.168.1.9 'cd /root'"
        //         sh "ssh root@192.168.1.9 'rm -rf taskcenter || true'"
        //         sh "ssh root@192.168.1.9 'mkdir taskcenter'"
                
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/taskcenter/target/taskcenter.jar root@192.168.1.9:~/taskcenter/'
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/taskcenter/Dockerfile root@192.168.1.9:~/taskcenter/'

        //         sh "ssh root@192.168.1.9 'docker stop taskcenter || true'"
        //         sh "ssh root@192.168.1.9 'docker rm taskcenter || true'"
        //         sh "ssh root@192.168.1.9 'docker rmi taskcenter ||true'"
        //         sh "ssh root@192.168.1.9 'docker build -t taskcenter /root/test-1'"
        //         sh "ssh root@192.168.1.9 'docker run -it -d -p : --name taskcenter taskcenter'"
        //     }
        // }

                
        
        // stage('Build usercreation') {
        //     steps {
        //         // Build the Spring Boot application using Maven
        //         sh 'cd usercreation && mvn clean package -DskipTests'
        //     }
        // }

        // stage('Deploy usercreation') {
        //     steps {

        //         sh "ssh root@192.168.1.9 'cd /root'"
        //         sh "ssh root@192.168.1.9 'rm -rf usercreation || true'"
        //         sh "ssh root@192.168.1.9 'mkdir usercreation'"
                
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/usercreation/target/usercreation.jar root@192.168.1.9:~/usercreation/'
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/usercreation/Dockerfile root@192.168.1.9:~/usercreation/'

        //         sh "ssh root@192.168.1.9 'docker stop usercreation || true'"
        //         sh "ssh root@192.168.1.9 'docker rm usercreation || true'"
        //         sh "ssh root@192.168.1.9 'docker rmi usercreation ||true'"
        //         sh "ssh root@192.168.1.9 'docker build -t usercreation /root/test-1'"
        //         sh "ssh root@192.168.1.9 'docker run -it -d -p : --name usercreation usercreation'"
        //     }
        // }

                        
        
        // stage('Build apigateway') {
        //     steps {
        //         // Build the Spring Boot application using Maven
        //         sh 'cd apigateway && mvn clean package -DskipTests'
        //     }
        // }

        // stage('Deploy apigateway') {
        //     steps {

        //         sh "ssh root@192.168.1.9 'cd /root'"
        //         sh "ssh root@192.168.1.9 'rm -rf apigateway || true'"
        //         sh "ssh root@192.168.1.9 'mkdir apigateway'"
                
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/apigateway/target/apigateway.jar root@192.168.1.9:~/apigateway/'
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/apigateway/Dockerfile root@192.168.1.9:~/apigateway/'

        //         sh "ssh root@192.168.1.9 'docker stop apigateway || true'"
        //         sh "ssh root@192.168.1.9 'docker rm apigateway || true'"
        //         sh "ssh root@192.168.1.9 'docker rmi apigateway ||true'"
        //         sh "ssh root@192.168.1.9 'docker build -t apigateway /root/test-1'"
        //         sh "ssh root@192.168.1.9 'docker run -it -d -p : --name apigateway apigateway'"
        //     }
        // }

        
                        
        
        // stage('Build workflow') {
        //     steps {
        //         // Build the Spring Boot application using Maven
        //         sh 'cd workflow && mvn clean package -DskipTests'
        //     }
        // }

        // stage('Deploy workflow') {
        //     steps {

        //         sh "ssh root@192.168.1.9 'cd /root'"
        //         sh "ssh root@192.168.1.9 'rm -rf workflow || true'"
        //         sh "ssh root@192.168.1.9 'mkdir workflow'"
                
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/workflow/target/workflow.jar root@192.168.1.9:~/workflow/'
        //         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/workflow/Dockerfile root@192.168.1.9:~/workflow/'

        //         sh "ssh root@192.168.1.9 'docker stop workflow || true'"
        //         sh "ssh root@192.168.1.9 'docker rm workflow || true'"
        //         sh "ssh root@192.168.1.9 'docker rmi workflow ||true'"
        //         sh "ssh root@192.168.1.9 'docker build -t workflow /root/test-1'"
        //         sh "ssh root@192.168.1.9 'docker run -it -d -p : --name workflow workflow'"
        //     }
        // }
        
    }
}
