pipeline {
    agent any
    stages {
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
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Integration Tests') {
            steps {
                // Integration tests, potentially with JMeter
                sh 'mvn verify -Pintegration-tests'
            }
        }
        stage('E2E Tests') {
            steps {
                // Run Selenium tests or Jest tests
                sh 'npm run e2e' // Modify this based on your setup
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
    }
}
pipeline {
    agent any
    stages {
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
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Integration Tests') {
            steps {
                // Integration tests, potentially with JMeter
                sh 'mvn verify -Pintegration-tests'
            }
        }
        stage('E2E Tests') {
            steps {
                // Run Selenium tests or Jest tests
                sh 'npm run e2e' // Modify this based on your setup
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
    }
}
