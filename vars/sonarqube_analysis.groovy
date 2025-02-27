// SonarQube Quality Analysis

def call(String sonarQubeToken, String sonarQubeProjectName, String sonarQubeProjectKey) {
    withSonarQubeEnv("${sonarQubeToken}") {
        sh "$SONAR_HOME/bin/sonar-scanner -Dsonar.projectKey=${sonarQubeProjectName} -Dsonar.projectName=${sonarQubeProjectKey}"
    }
}
