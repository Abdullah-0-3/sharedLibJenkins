// OWASP Check

def call(String odcName){
    dependencyCheck additionalArguments: '--scan ./', odcInstallation: '${odcName}'
    dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
}