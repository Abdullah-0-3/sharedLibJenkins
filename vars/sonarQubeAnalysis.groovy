def call(Map config = [:]) {
    def sonarQubeToken = config.sonarQubeToken ?: error("SonarQube token is required")
    def sonarQubeUrl = config.sonarQubeUrl ?: error("SonarQube URL is required")
    def sonarQubeProjectKey = config.sonarQubeProjectKey ?: error("SonarQube project key is required")
    def sonarQubeProjectName = config.sonarQubeProjectName ?: error("SonarQube project name is required")
    def soanrQubeInstallationName = config.sonarQubeInstallationName ?: error("SonarQube installation name is required")
    def sonarQubeCredentialsId = config.sonarQubeCredentialsId ?: error("SonarQube credentials ID is required")

    echo "Running SonarQube analysis..."

    withSonarQubeEnv(credentialsId: $sonarQubeCredentialsId, installationName: $soanrQubeInstallationName) {
        sh """
            sonar-scanner \
                -Dsonar.projectKey=${sonarQubeProjectKey} \
                -Dsonar.projectName=${sonarQubeProjectName} \
                -Dsonar.host.url=${sonarQubeUrl} \
                -Dsonar.login=${sonarQubeToken}
        """
    }

    echo "SonarQube analysis completed successfully"
}