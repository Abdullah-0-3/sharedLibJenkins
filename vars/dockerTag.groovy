def call(Map config = [:]) {
    def sourceImage = config.sourceImage ?: error("Source image is required")
    def sourceTag = config.sourceTag ?: 'latest'
    def targetImage = config.targetImage ?: sourceImage
    def targetTag = config.targetTag ?: error("Target tag is required")
    def dockerUsername = config.dockerUsername ?: error("Docker username is required")
    
    echo "Tagging Docker image..."
    
    sh """
        docker tag ${sourceImage}:${sourceTag} ${dockerUsername}/${targetImage}:${targetTag}
    """
    
    echo "Docker image tagged successfully"
}
