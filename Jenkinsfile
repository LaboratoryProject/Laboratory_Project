pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the version control system
                git 'https://github.com/LaboratoryProject/Laboratory_Project.git'
            }
        }

        stage('Build') {
            steps {
                // Build the Spring Boot application
                sh 'mvn clean package'
            }
        }

        stage('Unit Tests') {
            steps {
                // Run unit tests
                sh 'mvn test'
            }
            post {
                always {
                    // Publish JUnit test results
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Integration Tests') {
            steps {
                // Run integration tests with Maven
                sh 'mvn verify -Pintegration-tests'
            }
        }

        stage('E2E Tests') {
            steps {
                // Run end-to-end tests with npm
                sh 'npm run e2e' // Adjust this command according to your setup
            }
        }
    }

    post {
        always {
            // Archive the built JAR files
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
        success {
            // Notify on successful build
            echo 'Build was successful!'
        }
        failure {
            // Notify on build failure
            echo 'Build failed! Check the logs for details.'
        }
    }
}
