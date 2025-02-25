// Trivy Image Scan

def call(String imageName) {
    sh "trivy image --format table ${imageName} > trivy-image-scan.txt"
    // sh "trivy image --format json ${imageName}  > trivy-image-scan.json"
}