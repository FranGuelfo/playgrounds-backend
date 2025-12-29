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
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
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
