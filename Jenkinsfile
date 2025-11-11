pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Get source code from repository'
                checkout scm
            }
        }

        stage('Compile') {
                    steps {
                        echo 'Compile the project'
                        sh 'mvn clean compile'
                    }
                }
    }

}