// Docker Compose Up

def call() {
    sh "docker-compose up --build -d"
}