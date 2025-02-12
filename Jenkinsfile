#!/user/bin/env groovy
library identifier: 'jenkins-shared-library@main', retriever: modernSCM(
        [$class: 'GitSCMSource',
        remote: 'https://github.com/ncortim/jenkins-shared-library.git',
        credentialsId: 'github-repo'])

def gv

pipeline {   
    agent any
    tools {
        maven 'maven-3.9.9'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    buildJar()

                }
            }
        }

        stage("build image") {
            steps {
                script {
                    buildImage 'ncortim/demo-app:jma-1.5'
                    dockerLogin()
                    dockerPush 'ncortim/demo-app:jma-1.5'
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }               
    }
} 
