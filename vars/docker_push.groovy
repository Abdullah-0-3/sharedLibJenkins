// Docker Image Push

def call(String imageName, String userName, String credentialsId) {
    withCredentials([usernamePassword(
        credentialsId: credentialsId,
        usernameVariable: 'dockerHubUsername',
        passwordVariable: 'dockerHubPassword'
    )]) {
        sh "echo ${dockerHubPassword} | docker login -u ${dockerHubUsername} --password-stdin"
        sh "docker push ${userName}/${imageName}"
    }
}
