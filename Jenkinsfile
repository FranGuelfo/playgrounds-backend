pipeline {
    agent any

    tools {
        jdk '17'
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps { checkout scm }
        }

        stage('Build') {
            steps { sh 'mvn clean compile' }
        }

        stage('Test') {
            steps { sh 'mvn test' }
        }

        stage('Package') {
            steps { sh 'mvn package -DskipTests' }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t playgrounds-backend:latest .'
            }
        }
    }
}
