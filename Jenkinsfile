pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
        jdk 'jdk1.8'
    }

    environment {
        NAME = 'accounts-ms'
        AWS_REGION = 'us-east-2'
        GIT_COMMIT = env.GIT_COMMIT
    }

    stages {
/*
          stage ('Code Analysis') {
              steps {
                // Set SonarQube home directory, waiting for better way to do this
                script {
                    scannerHome = tool 'SonarQube Scanner 4.6'
                }
                // Run SonarQube scan using running EC2 instance
                withSonarQubeEnv('SonarQube Scanner') {
                    sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey= -Dsonar.sources=. -Dsonar.host.url=http://52.55.57.2:81 -Dsonar.login=75b0a4edafa1e19151b1e21e07f411e55af77e67"
                }
              }
          }
*/
    stage ('Package Maven Project') {
        steps {
            sh 'mvm clean package'
        }

    }

    stage ('Build Docker Image') {
        steps {
            sh 'docker build . -t ${NAME}'
        }
    }

    stage ('Push to ECR') {
        steps {
             withAWS(credentials: 'jenkins-credentials', region: '${AWS_REGION}') {
                    /*
                     * Pull account ID from jenkins-credentials AWS profile
                     * Login to AWS ECR for private repo access
                     * Push image to ECR
                     */
                    sh '''
                        AWS_ACCOUNT_ID=$(aws sts get-caller-identity | grep -oP \'(?<="Account": ")[^"]*\')
                        aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com
                        docker tag ${NAME}:latest ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${NAME}:latest
                        docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${NAME}:latest
                    '''
                }
        }
    }

    post {
        always {
            sh 'mvn clean'
            sh 'docker system prune -f'
        }
    }
}
