def buildJar() {
    echo 'building the application...'
    sh 'mvn package'
}

def buildImage() {
    echo 'building the docker image...'
    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASSWD', usernameVariable: 'USER')]) {
    sh 'docker image build -t ncortim/demo-app:jma-1.3 .'
    sh 'echo $PASSWD | docker login -u $USER --password-stdin'
    sh 'docker push ncortim/demo-app:jma-1.3'
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
