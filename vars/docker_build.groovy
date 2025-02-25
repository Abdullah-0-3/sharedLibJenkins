// Docker Code Build

def call(String imageName, String imageTag) {
    sh "docker build -t ${imageName}:${imageTag} ."
}