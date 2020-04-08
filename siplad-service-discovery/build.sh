mvn clean package install -DskipTests && \
sudo docker build -t siplad/service-discovery:1.0 .