pipeline {
    agent any
    tools {
        maven 'Maven_3.6.3'
        jdk 'jdk8'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
		
		stage ('Build Docker Image') {
            steps {
                sh 'docker build -t promotion-engine .' 
            }
        }
		
		stage ('Docker Run Image') {
            steps {
                sh 'docker run -dp 3000:8080 promotion-engine' 
            }
        }
    }
}