def call(Map config = [:]) {
    def imageTag = config.imageTag ?: error("Image tag is required")
    def manifestsPath = config.manifestsPath ?: 'kubernetes'
    def gitCredentials = config.gitCredentials ?: 'gitHubCredentials'
    def gitUserName = config.gitUserName ?: error("Git user name is required")
    def gitUserEmail = config.gitUserEmail ?: error("Git user email is required")
    def dockerHubUsername = config.dockerHubUsername ?: error("Docker Hub username is required")
    def appImageRepo = config.appImageRepo ?: 'easyshop-app'
    def migrationImageRepo = config.migrationImageRepo ?: 'easyshop-migration'
    def domain = config.domain ?: 'easyshop.letsdeployit.com'
    def gitHubUsername = config.gitHubUsername ?: error("GitHub username is required")
    def gitHubRepo = config.gitHubRepo ?: 'tws-e-commerce-app'
    def gitBranch = config.gitBranch ?: 'master'
    
    echo "Updating Kubernetes manifests with image tag: ${imageTag}"
    
    withCredentials([usernamePassword(
        credentialsId: gitCredentials,
        usernameVariable: 'GIT_USERNAME',
        passwordVariable: 'GIT_PASSWORD'
    )]) {
        // Configure Git
        sh """
            git config user.name "${gitUserName}"
            git config user.email "${gitUserEmail}"
        """
        
        // Update deployment manifests with new image tags - using proper Linux sed syntax
        sh """
            # Update main application deployment
            sed -i "s|image: ${dockerHubUsername}/${appImageRepo}:.*|image: ${dockerHubUsername}/${appImageRepo}:${imageTag}|g" ${manifestsPath}/08-easyshop-deployment.yaml
            
            # Update migration job if it exists
            if [ -f "${manifestsPath}/12-migration-job.yaml" ]; then
                sed -i "s|image: ${dockerHubUsername}/${migrationImageRepo}:.*|image: ${dockerHubUsername}/${migrationImageRepo}:${imageTag}|g" ${manifestsPath}/12-migration-job.yaml
            fi
            
            # Ensure ingress is using the correct domain
            if [ -f "${manifestsPath}/10-ingress.yaml" ]; then
                sed -i "s|host: .*|host: ${domain}|g" ${manifestsPath}/10-ingress.yaml
            fi
            
            # Check for changes
            if git diff --quiet; then
                echo "No changes to commit"
            else
                # Commit and push changes
                git add ${manifestsPath}/*.yaml
                git commit -m "Update image tags to ${imageTag} and ensure correct domain [ci skip]"
                
                # Set up credentials for push
                git remote set-url origin https://\${GIT_USERNAME}:\${GIT_PASSWORD}@github.com/${gitHubUsername}/${gitHubRepo}.git
                git push origin HEAD:\${gitBranch}
            fi
        """
    }
}