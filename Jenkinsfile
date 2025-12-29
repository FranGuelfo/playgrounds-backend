pipeline {
    agent any

    tools {
        jdk '17'
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build + Test + Coverage') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube') {
            environment {
                SONAR_TOKEN = credentials('sonar-token')
            }
            steps {
                sh '''
                mvn sonar:sonar \
                  -Dsonar.login=$SONAR_TOKEN
                '''
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished'
        }
        success {
            echo '✅ Build, tests and Sonar completed successfully'
        }
        failure {
            echo '❌ Pipeline failed'
        }
    }
}
