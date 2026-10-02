pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Report') {
		    steps {
		        publishHTML([
		            reportDir: 'test-output',
		            reportFiles: 'ExtentReport.html',
		            reportName: 'Extent Report',
		            keepAll: true,
		            alwaysLinkToLastBuild: true,
		            allowMissing: false
		        ])
		    }
		}

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully'
        }

        failure {
            echo 'Pipeline failed'
        }
    }
}
