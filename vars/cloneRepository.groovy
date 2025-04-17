def call(Map config = [:]) {
    def branch = config.branch ?: 'master'
    def repoUrl = config.repoUrl ?: error("Repository URL not provided")
    
    echo "Cloning Repository: $repoUrl"
    echo "Branch: $branch"

    sh """
        git clone -b ${branch} ${repoUrl} .
    """
    echo "Repository cloned successfully."
}