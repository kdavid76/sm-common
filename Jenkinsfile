
pipeline  {
    agent { label 'kubeagent' }
    environment {
        GITHUB_PATH = "https://${env.GITHUB_APIKEY}@github.com/kdavid76/sm-common.git"
    }
    tools {
        jdk 'oracle-jdk-17'
        maven 'mvn-3.8.7'
        git 'default'
    }
    stages {
    /*
        stage('Example') {
            steps {
                sh '''
                    env | grep -e PATH -e JAVA_HOME
                    which java
                    java -version
                    mvn -version
                    git --version
                    ls -la /home/jenkins/.m2
                '''
            }
        }
        stage('Environment variables') {
            steps {
                sh '''
                    printenv
                '''
            }
        }
        */

        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: env.BRANCH_NAME]],
                    userRemoteConfigs: [[url: env.GITHUB_PATH]]
                ])
            }
        }

        stage('Release') {
            when {
                branch 'master'
            }
            steps {
                sh '''
                    mvn release:prepare release:perform release:clean
                '''
            }
        }

        stage('Build') {
            steps {
                sh '''
                    mvn clean package -DskipTests=true
                '''
            }
        }

        stage('Static style check') {
            steps {
                sh '''
                    mvn ktlint:check
                '''
            }
        }

        stage('Test') {
            steps {
                sh '''
                    mvn test
                '''
            }
        }

        stage('Deploy to repository') {
            when {
                branch 'master'
            }
            steps {
                sh '''
                    mvn deploy
                '''
            }
        }
    }
}