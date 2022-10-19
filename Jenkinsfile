pipeline {
  agent any
  stages {
    stage('Build and test') {
      steps{
        withMaven {
          sh 'mvn clean deploy -f invesdwin-nowicket-parent/pom.xml -T4'
        }  
      }
    }
  }
}