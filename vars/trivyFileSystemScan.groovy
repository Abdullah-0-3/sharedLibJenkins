def call(){
  echo "Scanning file system..."
  sh "trivy fs . > trivy-fs-report.txt"
}