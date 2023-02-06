pipeline  {
    agent { label 'kubeagent' }
    tools {
        jdk 'oracle-jdk-17'
        maven 'mvn-3.8.7'
        git 'default'
    }
    stages {
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

        stage('Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'GithubKey', url: 'git@github.com:kdavid76/sm-common.git']])
            }
        }

        stage('Build') {
            steps {
                sh '''
                    mvn clean package -DskipTests=true
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
    }
}