// Docker Image Push

def call(String imageName, String credentialsId) {
    withCredentials([usernamePassword(
        credentialsId: credentialsId,
        usernameVariable: 'dockerHubUsername',
        passwordVariable: 'dockerHubPassword'
    )]) {
        sh "echo ${env.dockerHubPassword} | docker login -u ${env.dockerHubUsername} --password-stdin"
        sh "docker push ${env.userName}/${imageName}"
    }
}
