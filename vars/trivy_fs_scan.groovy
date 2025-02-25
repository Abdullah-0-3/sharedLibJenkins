// Trivy File System Scan

def call() {
    sh "trivy fs --format json . > trivy-fs-scan.json"
}