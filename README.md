# DOCKER

- mvn -N wrapper:wrapper -Dmaven=3.5.2
- docker buildx build --platform linux/amd64 -t chris0100eka/users . -f ./msvc-users/Dockerfile
- docker buildx build --platform linux/amd64 -t chris0100eka/arithmetic-calculator . -f ./msvc-arithmetic-calculator/Dockerfile
- docker push chris0100eka/users
- docker push chris0100eka/arithmetic-calculator
- docker network create calculator
- docker-compose up -d