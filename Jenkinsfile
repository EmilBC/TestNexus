def gv_script
pipeline{
    agent any

    stages{
        stage('Initialize Docker'){    
	   steps{
	          script{
	  env.PATH = "${dockerHome}/bin:${env.PATH}"     
		  }
	   }
    }
        stage('Init'){
           
            steps{
                  echo "1."
                script{
                    gv_script = load "script.groovy"
                }
            }
        }

        stage('Build'){
             
            steps{
                 echo "2."
                script{
                    gv_script.buildApp()
                }
            }
        }

        stage('Test'){
            steps{
                script{
                    gv_script.testApp()
                }
            }
        }

        stage('Push image'){
            steps{
                script{
                    gv_script.pushImage()
                }
            }
        }

        stage('Deploy'){
            steps{
                script{
                    gv_script.deployApp()
                }
            }
        }

        stage('Commit'){
            steps{
                script{
                    gv_script.commitChanges()
                }
            }
        }
    }
    post{
        success{
            sh 'echo "Pipeline created successfully!"'
        }
    }
}
