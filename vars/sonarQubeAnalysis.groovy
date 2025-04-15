def call(Map config = [:]) {
    def sonarQubeTokenName = config.sonarQubeTokenName ?: error("SonarQube token name is required")
    def sonarQubeProjectKey = config.sonarQubeProjectKey ?: error("SonarQube project key is required")
    def sonarQubeProjectName = config.sonarQubeProjectName ?: error("SonarQube project name is required")
    def sonarQubeInstallationName = config.sonarQubeInstallationName ?: error("SonarQube installation name is required")
    def sonarQubeScannerHome = config.sonarQubeScannerHome ?: error("SonarQube scanner home is required")

    echo "Running SonarQube analysis..."

    withSonarQubeEnv(credentialsId: "${sonarQubeTokenName}", installationName: "${sonarQubeInstallationName}") { 
        sh """
            ${sonarQubeScannerHome}/bin/sonar-scanner \
                -Dsonar.projectKey=${sonarQubeProjectKey} \
                -Dsonar.projectName=${sonarQubeProjectName} \
                -X
        """
    }

    echo "SonarQube analysis completed successfully"
}