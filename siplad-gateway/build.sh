mvn clean package install -DskipTests && \
sudo docker build -t siplad/service-gateway:1.0 .