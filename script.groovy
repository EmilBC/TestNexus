def buildApp(){
    echo "Building Application ..."
}


def pushImage(){
    echo 'Building Image ...'
     sh "docker -H tcp://5.tcp.eu.ngrok.io:16361 build -t devopsexamplenew1234567:${env.BUILD_NUMBER} ."

    echo 'Pushing image to docker hosted rerpository on Nexus'

withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PSW', usernameVariable: 'USER')]){
        sh "echo ${PSW} | docker login -u ${USER} --password-stdin  https://03a8-2a01-cb06-b031-4ee1-d847-9253-6f0c-4e21.ngrok-free.app"
        //sh "docker push https://03a8-2a01-cb06-b031-4ee1-d847-9253-6f0c-4e21.ngrok-free.app/devopsexamplenew1234567:${env.BUILD_NUMBER}"
    }
}

def testApp(){
    echo 'Testing Application ...'
}

def deployApp(){
    echo 'Deploying Application ...'
}

def commitChanges(){
      //  withCredentials([usernamePassword(credentialsId: 'github', passwordVariable: 'PSW', usernameVariable: 'USER')]) {
       // sh 'git config --global user.name "jenkins"'
       // sh 'git config --global user.email "my.jenkins.server@gmail.com"'
       // sh "git remote set-url origin https://${USER}:${PSW}@github.com/BhairaviSanskriti/nexus-CI-pipeline-for-portfolio.git"

      //  sh '''
        //    #!/bin/bash
          //  sed -i 's/Version:.*/Version: '"${BUILD_NUMBER}"'/g' index.html
       // '''
        
        //sh "git add ."
        //sh 'git commit -m "updated version"'
      //  sh "git push origin HEAD:main"
  //}
  echo 'Changes committed by jenkins'
}

return this
