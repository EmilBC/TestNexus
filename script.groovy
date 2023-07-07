def buildApp(){
    echo "Building Application ..."
}


def pushImage(){
    echo 'Building Image ...'
    sh "docker build -t tcp://5.tcp.eu.ngrok.io:19844/nexustest:${BUILD_NUMBER} ."

    echo 'Pushing image to docker hosted rerpository on Nexus'

withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PSW', usernameVariable: 'USER')]){
        sh "echo ${PSW} | docker login -u ${USER} --password-stdin  https://ffea-2a01-cb06-a04a-8df2-c58a-5ffd-bc36-458b.ngrok-free.app"
        sh "docker push  https://ffea-2a01-cb06-a04a-8df2-c58a-5ffd-bc36-458b.ngrok-free.app/nexustest:${BUILD_NUMBER}"
    }
}

def testApp(){
    echo 'Testing Application ...'
}

def deployApp(){
    echo 'Deploying Application ...'
}

def commitChanges(){
        withCredentials([usernamePassword(credentialsId: 'github', passwordVariable: 'PSW', usernameVariable: 'USER')]) {
        sh 'git config --global user.name "jenkins"'
        sh 'git config --global user.email "my.jenkins.server@gmail.com"'
        sh "git remote set-url origin https://${USER}:${PSW}@github.com/BhairaviSanskriti/nexus-CI-pipeline-for-portfolio.git"

        sh '''
            #!/bin/bash
            sed -i 's/Version:.*/Version: '"${BUILD_NUMBER}"'/g' index.html
        '''
        
        sh "git add ."
        sh 'git commit -m "updated version"'
        sh "git push origin HEAD:main"
  }
  echo 'Changes committed by jenkins'
}

return this
