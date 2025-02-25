// Docker Image Tag

def call(String imageName, String userName, String imageTag) {
    sh "docker tag ${imageName} ${userName}/${imageTag}"
}