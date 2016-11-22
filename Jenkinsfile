node {
    // Get the maven tool.
    // ** NOTE: This 'M3' maven tool must be configured
    // **       in the Jenkins global configuration.
    def mvnHome = tool 'M3'
    sh "echo ${mvnHome}"
    
    
    // Mark the code checkout 'stage'....
    stage 'Checkout'
    // Get some code from a GitHub repository
    checkout scm    
   
    // Mark the code build 'stage'....
    stage 'Build ajsc5'
    // Run the maven build
    //sh for unix bat for windows
   
    sh "${mvnHome}/bin/mvn -f ajsc5-camel-components-master/pom.xml clean deploy"
    
    
}
