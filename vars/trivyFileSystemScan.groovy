def call(){
  echo "Scanning file system..."
  sh "trivy fs . > trivy-report.txt"
}