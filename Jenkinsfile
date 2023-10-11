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

                sh "ssh root@192.168.1.9 'rm -rf ~/eureka.jar || true'"
                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/Eureka-Server/target/eureka.jar root@192.168.1.9:~/'
                sh "ssh root@192.168.1.9 'docker stop eureka || true'"
                sh "ssh root@192.168.1.9 'docker rm eureka || true'"
                sh "ssh root@192.168.1.9 'docker rmi eureka ||true'"
            }
        }


        stage('Build api-gateway') {
            steps {
                // Build the Spring Boot application using Maven
                sh 'cd api-gateway && mvn clean package -DskipTests'
                
                sh "ssh root@192.168.1.9 'rm -rf ~/api-gateway.jar || true'"
                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/api-gateway/target/api-gateway.jar root@192.168.1.9:~/'
                sh "ssh root@192.168.1.9 'docker stop api-gateway || true'"
                sh "ssh root@192.168.1.9 'docker rm api-gateway || true'"
                sh "ssh root@192.168.1.9 'docker rmi api-gateway ||true'"

            }
        }

        
        stage('Build auth-server') {
            steps {
                // Build the Spring Boot application using Maven
                sh 'cd auth-server && mvn clean package -DskipTests'
                
                sh "ssh root@192.168.1.9 'rm -rf ~/auth-server.jar || true'"
                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/auth-server/target/auth-server.jar root@192.168.1.9:~/'
                sh "ssh root@192.168.1.9 'docker stop auth-server || true'"
                sh "ssh root@192.168.1.9 'docker rm auth-server || true'"
                sh "ssh root@192.168.1.9 'docker rmi auth-server ||true'"

            }
        }

        
        stage('Build SuperAdmin') {
            steps {
                // Build the Spring Boot application using Maven
                sh 'cd SuperAdmin && mvn clean package -DskipTests'
                
                sh "ssh root@192.168.1.9 'rm -rf ~/superadmin.jar || true'"
                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/SuperAdmin/target/superadmin.jar root@192.168.1.9:~/'
                sh "ssh root@192.168.1.9 'docker stop SuperAdmin || true'"
                sh "ssh root@192.168.1.9 'docker rm SuperAdmin || true'"
                sh "ssh root@192.168.1.9 'docker rmi SuperAdmin ||true'"
            }
        }

        stage('Build notification') {
                    steps {
                        // Build the Spring Boot application using Maven
                        sh 'cd notification && mvn clean package -DskipTests'

                        sh "ssh root@192.168.1.9 'rm -rf ~/notification.jar || true'"
                        sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/notification/target/notification.jar root@192.168.1.9:~/'
                        sh "ssh root@192.168.1.9 'docker stop notification || true'"
                        sh "ssh root@192.168.1.9 'docker rm notification || true'"
                        sh "ssh root@192.168.1.9 'docker rmi notification ||true'"
                    }
                }

        stage('Build community') {
                            steps {
                                // Build the Spring Boot application using Maven
                                sh 'cd community && mvn clean package -DskipTests'

                                sh "ssh root@192.168.1.9 'rm -rf ~/community.jar || true'"
                                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/community/target/community.jar root@192.168.1.9:~/'
                                sh "ssh root@192.168.1.9 'docker stop community || true'"
                                sh "ssh root@192.168.1.9 'docker rm community || true'"
                                sh "ssh root@192.168.1.9 'docker rmi community ||true'"
                            }
                        }

        stage('Deploy All Microservices') { 
            steps {
                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/docker-compose.yml root@192.168.1.9:~/'
                sh " ssh root@192.168.1.9 'docker-compose up -d'"
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
