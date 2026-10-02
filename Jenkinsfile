pipeline {
    agent any
    
    parameters {
    	choice(
    		name: 'TEST_ENV',
    		choice: ['QA', 'STAGE', 'PROD'],
    		description: 'Select Environment'
    	)
    	choice(
    		name: 'BROWSER',
    		choice: ['chrome', 'edge'],
    		description: 'Select Browser'
    	)
    }
    
    environment {
       PROJECT_NAME = 'OrangeHRM'
       REPORT_DIR = 'test-output'
    }

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
            	bat 'echo Environment: %TEST_ENV%'
                bat 'echo Browser: %BROWSER%'
                bat 'echo Project: %PROJECT_NAME%'
                bat 'mvn test -DtestEnv=%TEST_ENV% -Dbrowser=%BROWSER%'
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
