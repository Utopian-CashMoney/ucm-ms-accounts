pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
        jdk 'jdk1.8'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
        withAWS(credentials: 'jenkins-credentials', region: 'us-east-2')
    }

    environment {
        NAME = 'accounts-ms'
        AWS_REGION = 'us-east-2'
        GIT_COMMIT = '${env.GIT_COMMIT}'
    }

    stages {
        stage ('Pull Git Submodules') {
            steps {
                sh "git submodule update --init --recursive"
            }
        }

        // stage ('Code Analysis: ucm-lib') {
        //     steps {
        //         // Set SonarQube home directory, waiting for better way to do this
        //         script {
        //             scannerHome = tool 'SonarQube Scanner 4.6'
        //         }
        //         // Run SonarQube scan using running EC2 instance
        //         withSonarQubeEnv('SonarQube Scanner') {
        //             sh "mvn -f ucm-lib/ sonar:sonar -Dsonar.host.url=http://sonar.utopiancashmoney.de -Dsonar.login=6bb400486dba3afb9b5592a2955daeb656491d65"
        //         }
        //     }
        // }

        stage ('Install Maven: ucm-lib') {
            steps {
                // Install git submodule of ucm-lib
                sh 'mvn -f ucm-lib/ clean install'
            }
        }

        // stage ('Code Analysis') {
        //     steps {
        //         // Set SonarQube home directory, waiting for better way to do this
        //         script {
        //             scannerHome = tool 'SonarQube Scanner 4.6'
        //         }
        //         // Run SonarQube scan using running EC2 instance
        //         withSonarQubeEnv('SonarQube Scanner') {
        //             sh "mvn sonar:sonar -Dsonar.host.url=http://sonar.utopiancashmoney.de -Dsonar.login=0f2c1612c907a52c0256b61ae2fdb9fd70407a8b"
        //         }
        //     }
        // }

        stage ('Package Maven Project') {
            steps {
                // Package project
                sh 'mvn clean package -DskipTests'
            }
        }

        stage ('Build Docker Image') {
            steps {
                sh 'docker build . -t ${NAME}'
            }
        }

        stage ('Push to ECR') {
            steps {
                /*
                * Pull account ID from jenkins-credentials AWS profile
                * Login to AWS ECR for private repo access
                * Push image to ECR
                */
                
                script {
                    def aws_account_id = awsIdentity().account
                    sh ecrLogin()
                    sh "docker tag ${NAME}:latest ${aws_account_id}.dkr.ecr.${AWS_REGION}.amazonaws.com/${NAME}:latest"
                    sh "docker push ${aws_account_id}.dkr.ecr.${AWS_REGION}.amazonaws.com/${NAME}:latest"
                }
                /*
                sh '''
                    aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${IDENTITY.account}.dkr.ecr.${AWS_REGION}.amazonaws.com
                    docker tag ${NAME}:latest ${IDENTITY.account}.dkr.ecr.${AWS_REGION}.amazonaws.com/${NAME}:latest
                    docker push ${IDENTITY.account}.dkr.ecr.${AWS_REGION}.amazonaws.com/${NAME}:latest
                '''
                */
            }
        }
    }

    post {
        always {
            sh 'mvn -f ucm-lib/ clean'
            sh 'mvn clean'
            sh 'docker system prune -f'
            cleanWs()
        }
    }
}
