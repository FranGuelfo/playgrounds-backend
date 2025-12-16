pipeline {
    agent any

    tools {
        jdk 'java-17'
        maven 'maven-3'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Clonando repositorio...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compilando proyecto...'
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Ejecutando tests...'
                sh 'mvn test'
            }
        }

    }

    post {
        success {
            echo '✅ Build y tests OK'
        }
        failure {
            echo '❌ Build o tests fallaron'
        }
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}

