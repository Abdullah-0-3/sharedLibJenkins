// SonarQube Analysis - Safe

def call(String sonarCredentialsId, String SONARQUBE_SCANNER) {
    withSonarQubeEnv(credentialsId: "${sonarCredentialsId}", installationName: "${SONARQUBE_SCANNER}") {
        sh "$SONAR_HOME/bin/sonar-scanner -Dsonar.projectKey=${env.JOB_NAME} -Dsonar.projectKey=${env.JOB_NAME}"
    }
}