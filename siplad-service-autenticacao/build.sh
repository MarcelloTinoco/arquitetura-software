mvn clean package install -DskipTests && \
sudo docker build -t siplad/service-autenticacao:1.0 .