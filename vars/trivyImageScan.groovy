def call(Map config = [:]) {
    def imageName = config.imageName ?: error("Image name is required")
    def imageTag = config.imageTag ?: 'latest'
    
    echo "Scanning Docker image..."
    
    sh """
        trivy image --format table ${imageName}:${imageTag} > trivy-report.txt
    """
    
    echo "Docker image scanned successfully"
}