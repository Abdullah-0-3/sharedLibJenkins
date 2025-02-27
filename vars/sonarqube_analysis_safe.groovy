// SonarQube Analysis - Safe

def call(String sonarCredentialsId, String SONARQUBE_SCANNER, String sonarHome) {
    withSonarQubeEnv(credentialsId: $sonarCredentialsId, installationName: $SONARQUBE_SCANNER) {
        sh "${sonarHome}/bin/sonar-scanner -Dsonar.projectKey=${env.JOB_NAME} -Dsonar.projectKey=${env.JOB_NAME}"
    }
}