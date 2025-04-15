def call() {
    echo "Checking SonarQube gates..."

    timeout(time: 12, unit: "MINUTES") {
        waitForQualityGate abortPipeline: false
    }

    echo "SonarQube gates check completed successfully."
}