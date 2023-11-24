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

                 sh "ssh root@192.168.0.9 'cd /root'"
                 sh "ssh root@192.168.0.9 'rm -rf eureka || true'"
                 sh "ssh root@192.168.0.9 'mkdir eureka'"

                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/Eureka-Server/target/eureka.jar root@192.168.0.9:~/eureka/'

                sh "ssh root@192.168.0.9 'docker stop root_eureka_1 || true'"
                sh "ssh root@192.168.0.9 'docker rm root_eureka_1 || true'"
                sh "ssh root@192.168.0.9 'docker rmi root_eureka_1 ||true'"
            }
        }


        stage('Build api-gateway') {
            steps {
                // Build the Spring Boot application using Maven
                sh 'cd api-gateway && mvn clean package -DskipTests'

                 sh "ssh root@192.168.0.9 'cd /root'"
                 sh "ssh root@192.168.0.9 'rm -rf api-gateway || true'"
                 sh "ssh root@192.168.0.9 'mkdir api-gateway'"

                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/api-gateway/target/api-gateway.jar root@192.168.0.9:~/api-gateway/'

                sh "ssh root@192.168.0.9 'docker stop root_api-gateway_1 || true'"
                sh "ssh root@192.168.0.9 'docker rm root_api-gateway_1 || true'"
                sh "ssh root@192.168.0.9 'docker rmi root_api-gateway_1 ||true'"

            }
        }

        
        stage('Build auth-server') {
            steps {
                // Build the Spring Boot application using Maven
                 sh 'cd auth-server && mvn clean package -DskipTests'

                 sh "ssh root@192.168.0.9 'cd /root'"
                 sh "ssh root@192.168.0.9 'rm -rf auth-server || true'"
                 sh "ssh root@192.168.0.9 'mkdir auth-server'"

                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/auth-server/target/auth-server.jar root@192.168.0.9:~/auth-server/'
                sh "ssh root@192.168.0.9 'docker stop root_auth-server_1 || true'"
                sh "ssh root@192.168.0.9 'docker rm root_auth-server_1 || true'"
                sh "ssh root@192.168.0.9 'docker rmi root_auth-server_1 ||true'"

            }
        }

        
        stage('Build SuperAdmin') {
            steps {
                // Build the Spring Boot application using Maven
                sh 'cd SuperAdmin && mvn clean package -DskipTests'

                sh "ssh root@192.168.0.9 'cd /root'"
                sh "ssh root@192.168.0.9 'rm -rf superadmin || true'"
                sh "ssh root@192.168.0.9 'mkdir superadmin'"

                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/SuperAdmin/target/superadmin.jar root@192.168.0.9:~/superadmin/'

                sh "ssh root@192.168.0.9 'docker stop root_superadmin_1 || true'"
                sh "ssh root@192.168.0.9 'docker rm root_superadmin_1 || true'"
                sh "ssh root@192.168.0.9 'docker rmi root_superadmin_1 ||true'"
            }
        }

        stage('Build notification') {
                    steps {
                        // Build the Spring Boot application using Maven
                        sh 'cd notification && mvn clean package -DskipTests'


                        sh "ssh root@192.168.0.9 'cd /root'"
                        sh "ssh root@192.168.0.9 'rm -rf notification || true'"
                        sh "ssh root@192.168.0.9 'mkdir notification'"

                        sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/notification/target/notification.jar root@192.168.0.9:~/notification/'

                        sh "ssh root@192.168.0.9 'docker stop root_notification_1 || true'"
                        sh "ssh root@192.168.0.9 'docker rm root_notification_1 || true'"
                        sh "ssh root@192.168.0.9 'docker rmi root_notification_1 ||true'"
                    }
                }

        stage('Build community') {
                            steps {
                                // Build the Spring Boot application using Maven
                                sh 'cd community && mvn clean package -DskipTests'


                                sh "ssh root@192.168.0.9 'cd /root'"
                                sh "ssh root@192.168.0.9 'rm -rf community || true'"
                                sh "ssh root@192.168.0.9 'mkdir community'"

                                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/community/target/community.jar root@192.168.0.9:~/community/'

                                sh "ssh root@192.168.0.9 'docker stop root_community_1 || true'"
                                sh "ssh root@192.168.0.9 'docker rm root_community_1 || true'"
                                sh "ssh root@192.168.0.9 'docker rmi root_community_1 || true'"
                            }
                        }

         stage('Build suggestion') {
                                     steps {
                                         // Build the Spring Boot application using Maven
                                         sh 'cd suggestion && mvn clean package -DskipTests'

                                         sh "ssh root@192.168.0.9 'cd /root'"
                                         sh "ssh root@192.168.0.9 'rm -rf suggestion || true'"
                                         sh "ssh root@192.168.0.9 'mkdir suggestion'"

                                         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/suggestion/target/suggestion.jar root@192.168.0.9:~/suggestion/'

                                         sh "ssh root@192.168.0.9 'docker stop root_suggestion_1 || true'"
                                         sh "ssh root@192.168.0.9 'docker rm root_suggestion_1 || true'"
                                         sh "ssh root@192.168.0.9 'docker rmi root_suggestion_1 || true'"
                                     }
                                 }

         stage('Build taskcenter') {
                                     steps {
                                         // Build the Spring Boot application using Maven
                                         sh 'cd taskcenter && mvn clean package -DskipTests'

                                         sh "ssh root@192.168.0.9 'cd /root'"
                                         sh "ssh root@192.168.0.9 'rm -rf taskcenter || true'"
                                         sh "ssh root@192.168.0.9 'mkdir taskcenter'"

                                         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/taskcenter/target/taskcenter.jar root@192.168.0.9:~/taskcenter/'

                                         sh "ssh root@192.168.0.9 'docker stop root_task-center_1 || true'"
                                         sh "ssh root@192.168.0.9 'docker rm root_task-center_1 || true'"
                                         sh "ssh root@192.168.0.9 'docker rmi root_task-center_1 || true'"
                                     }
                                 }


         stage('Build broadcast') {
                                     steps {
                                         // Build the Spring Boot application using Maven
                                         sh 'cd broadcast && mvn clean package -DskipTests'

                                         sh "ssh root@192.168.0.9 'cd /root'"
                                         sh "ssh root@192.168.0.9 'rm -rf broadcast || true'"
                                         sh "ssh root@192.168.0.9 'mkdir broadcast'"

                                         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/broadcast/target/broadcast.jar root@192.168.0.9:~/broadcast/'

                                         sh "ssh root@192.168.0.9 'docker stop root_broadcast_1 || true'"
                                         sh "ssh root@192.168.0.9 'docker rm root_broadcast_1 || true'"
                                         sh "ssh root@192.168.0.9 'docker rmi root_broadcast_1 || true'"
                                     }
                                 }

         stage('Build media-service') {
                                     steps {
                                         // Build the Spring Boot application using Maven
                                         sh 'cd media-service && mvn clean package -DskipTests'

                                         sh "ssh root@192.168.0.9 'cd /root'"
                                         sh "ssh root@192.168.0.9 'rm -rf mediaservice || true'"
                                         sh "ssh root@192.168.0.9 'mkdir mediaservice'"

                                         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/media-service/target/mediaservice.jar root@192.168.0.9:~/mediaservice/'

                                         sh "ssh root@192.168.0.9 'docker stop root_mediaservice_1 || true'"
                                         sh "ssh root@192.168.0.9 'docker rm root_mediaservice_1 || true'"
                                         sh "ssh root@192.168.0.9 'docker rmi root_mediaservice_1 || true'"
                                     }
                                 }

         stage('Build attendance') {
                                     steps {
                                         // Build the Spring Boot application using Maven
                                         sh 'cd attendance && mvn clean package -DskipTests'

                                         sh "ssh root@192.168.0.9 'cd /root'"
                                         sh "ssh root@192.168.0.9 'rm -rf attendance || true'"
                                         sh "ssh root@192.168.0.9 'mkdir attendance'"

                                         sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/media-service/target/attendance.jar root@192.168.0.9:~/attendance/'

                                         sh "ssh root@192.168.0.9 'docker stop root_attendance_1 || true'"
                                         sh "ssh root@192.168.0.9 'docker rm root_attendance_1 || true'"
                                         sh "ssh root@192.168.0.9 'docker rmi root_attendance_1 || true'"
                                     }
                                 }


        stage('Deploy All Microservices') { 
            steps {
                sh "ssh root@192.168.0.9 'cd /root'"
                sh "ssh root@192.168.0.9 'rm -rf docker-compose.yml || true'"
                sh ' scp -i id_rsa /var/jenkins_home/workspace/bricknet/docker-compose.yml root@192.168.0.9:~/'
                sh " ssh root@192.168.0.9 'docker-compose up -d'"
            }
        }
        
    }
}
